package org.foodie_tour.modules.booking.service.impl;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.common.exception.InvalidateDataException;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.modules.booking.dto.request.CustomBookingRequest;
import org.foodie_tour.modules.booking.dto.response.BookingResponse;
import org.foodie_tour.modules.booking.entity.Booking;
import org.foodie_tour.modules.booking.enums.BookingStatus;
import org.foodie_tour.modules.booking.mapper.BookingMapper;
import org.foodie_tour.modules.booking.repository.BookingRepository;
import org.foodie_tour.modules.booking.service.CustomTourService;
import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.foodie_tour.modules.routes.entity.Route;
import org.foodie_tour.modules.routes.entity.RouteCheckpoint;
import org.foodie_tour.modules.routes.enums.CheckPointType;
import org.foodie_tour.modules.routes.enums.RouteStatus;
import org.foodie_tour.modules.routes.repository.CheckPointRepository;
import org.foodie_tour.modules.routes.repository.RouteRepository;
import org.foodie_tour.modules.schedules.entity.Schedule;
import org.foodie_tour.modules.schedules.repository.ScheduleRepository;
import org.foodie_tour.modules.tours.entity.Tour;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomTourServiceImpl implements CustomTourService {


    private final ScheduleRepository scheduleRepository;
    private final CheckPointRepository checkPointRepository;
    private final RouteRepository routeRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingResponse createCustomBooking(CustomBookingRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Lịch trình không tồn tại"));

        Tour tour = schedule.getTour();

        List<CheckPoint> selectCps = checkPointRepository.findAllById(request.getSelectedCheckpointIds());
        validateCustomConstraints(tour, selectCps);

        Route customRoute = createNewCustomRoute(tour, selectCps, request.getCustomerName());

        Booking booking = bookingMapper.toEntity(request);
        booking.setSchedule(schedule);
        booking.setTour(tour);
        booking.setRoute(customRoute);
        booking.setBookingCode("CB-" + System.currentTimeMillis());
        booking.setBookingStatus(BookingStatus.PENDING);

        Long total = (request.getAdultCount() * tour.getBasePriceAdult()) +
                (request.getChildrenCount() * tour.getBasePriceChild());
        booking.setTotalPrice(total);

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);
    }

    private Route createNewCustomRoute(Tour tour, List<CheckPoint> cps, String name) {
        Route route = new Route();
        route.setRouteName("Lộ trình của " + name);
        route.setTour(tour);
        route.setRouteStatus(RouteStatus.ACTIVE);

        List<RouteCheckpoint> rcpList = new ArrayList<>();
        for (int i = 0; i < cps.size(); i++) {
            RouteCheckpoint rcp = new RouteCheckpoint();
            rcp.setRoute(route);
            rcp.setCheckPoint(cps.get(i));
            rcp.setOrder(i + 1);
            rcpList.add(rcp);
        }
        route.setRouteCheckpoints(rcpList);
        return routeRepository.save(route);
    }

    private void validateCustomConstraints(Tour tour, List<CheckPoint> selectedCPs) {
        int requiredTotal = (tour.getTotalCustomPlaces() != null) ? tour.getTotalCustomPlaces() : 5;
        int minFood = (tour.getMinFoodPlaces() != null) ? tour.getMinFoodPlaces() : 0;
        int minVisit = (tour.getMinVisitPlaces() != null) ? tour.getMinVisitPlaces() : 0;

        if (selectedCPs.size() != requiredTotal) {
            throw new InvalidateDataException(
                    String.format("Bạn phải chọn chính xác %d địa điểm cho tour này (Hiện tại: %d)",
                            requiredTotal, selectedCPs.size())
            );
        }

        long foodCount = selectedCPs.stream()
                .filter(cp -> cp.getType() == CheckPointType.FOOD)
                .count();

        long visitCount = selectedCPs.stream()
                .filter(cp -> cp.getType() == CheckPointType.VISIT)
                .count();

        if (foodCount < minFood) {
            throw new InvalidateDataException(
                    String.format("Lộ trình custom phải có tối thiểu %d địa điểm ăn uống (FOOD). Bạn mới chọn %d.",
                            minFood, foodCount)
            );
        }

        if (visitCount < minVisit) {
            throw new InvalidateDataException(
                    String.format("Lộ trình custom phải có tối thiểu %d địa điểm tham quan (VISIT). Bạn mới chọn %d.",
                            minVisit, visitCount)
            );
        }

        for (CheckPoint cp : selectedCPs) {
            if (!cp.getTour().getTourId().equals(tour.getTourId())) {
                throw new InvalidateDataException(
                        String.format("Địa điểm '%s' không thuộc về tour này. Vui lòng kiểm tra lại.",
                                cp.getLocationName())
                );
            }
        }
    }
}
