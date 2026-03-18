package org.foodie_tour.modules.routes.service.impl;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.common.exception.DuplicateResourceException;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.modules.routes.dto.request.CheckPointOrderRequest;
import org.foodie_tour.modules.routes.dto.request.RouteRequest;
import org.foodie_tour.modules.routes.dto.response.RouteResponse;
import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.foodie_tour.modules.routes.entity.Route;
import org.foodie_tour.modules.routes.entity.RouteCheckpoint;
import org.foodie_tour.modules.routes.enums.RouteCheckPointStatus;
import org.foodie_tour.modules.routes.enums.RouteStatus;
import org.foodie_tour.modules.routes.mapper.RouteMapper;
import org.foodie_tour.modules.routes.repository.CheckPointRepository;
import org.foodie_tour.modules.routes.repository.RouteRepository;
import org.foodie_tour.modules.routes.service.RouteService;
import org.foodie_tour.modules.tours.entity.Tour;
import org.foodie_tour.modules.tours.repository.TourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final TourRepository tourRepository;
    private final CheckPointRepository checkPointRepository;

    @Override
    @Transactional
    public RouteResponse createRoute(RouteRequest routeRequest) {
        if (routeRepository.existsByRouteName(routeRequest.getRouteName())) {
            throw new DuplicateResourceException("Tuyến đường này đã tồn tại");
        }

        Tour tour = tourRepository.findById(routeRequest.getTourId())
                .orElseThrow(() -> new ResourceNotFoundException("Tour không tồn tại"));

        Route route = routeMapper.toEntity(routeRequest);
        route.setTour(tour);
        route.setCreatedAt(LocalDateTime.now());

        List<RouteCheckpoint> routeCheckpoints = new ArrayList<>();
        if (routeRequest.getCheckPointOrderRequests() != null) {
            for (CheckPointOrderRequest orderReq : routeRequest.getCheckPointOrderRequests()) {
                CheckPoint cp = checkPointRepository.findById(orderReq.getCheckpointId())
                        .orElseThrow(() -> new ResourceNotFoundException("Địa điểm không tồn tại: " + orderReq.getCheckpointId()));

                RouteCheckpoint rcp = RouteCheckpoint.builder()
                        .route(route)
                        .checkPoint(cp)
                        .order(orderReq.getOrder())
                        .status(RouteCheckPointStatus.ACTIVE)
                        .build();
                routeCheckpoints.add(rcp);
            }
        }
        route.setRouteCheckpoints(routeCheckpoints);

        routeRepository.save(route);
        return routeMapper.toResponse(route);
    }

    @Override
    public List<RouteResponse> getAllRoutes(RouteStatus routeStatus) {
        List<Route> routes;
        if (routeStatus != null) {
            routes = routeRepository.findRouteByRouteStatus(routeStatus);
        } else {
            routes = routeRepository.findAll();
        }

        return routes.stream()
                .map(routeMapper::toResponse)
                .toList();
    }

    @Override
    public RouteResponse getRouteById(Long routeId, RouteStatus routeStatus) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tuyến đường không tồn tại"));

        if (routeStatus != null && !route.getRouteStatus().equals(routeStatus)) {
            throw new ResourceNotFoundException("Tuyến đường không tồn tại với trạng thái");
        }

        return routeMapper.toResponse(route);
    }

    @Override
    public RouteResponse updateRouteById(Long routeId, RouteRequest routeRequest) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tuyến đường không tồn tại"));

        Tour tour = tourRepository.findById(routeRequest.getTourId())
                .orElseThrow(() -> new ResourceNotFoundException("Tour không tồn tại"));

        routeMapper.updateEntity(routeRequest, route);
        route.setUpdatedAt(LocalDateTime.now());
        route.setTour(tour);

        routeRepository.save(route);
        return routeMapper.toResponse(route);
    }

    @Override
    public void deleteRouteById(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tuyến đường này không tồn tại"));

        route.setRouteStatus(RouteStatus.DELETED);
        routeRepository.save(route);
    }
}
