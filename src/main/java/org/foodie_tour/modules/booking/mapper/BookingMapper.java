package org.foodie_tour.modules.booking.mapper;

import org.foodie_tour.modules.booking.dto.request.BookingCreateRequest;
import org.foodie_tour.modules.booking.dto.request.CustomBookingRequest;
import org.foodie_tour.modules.booking.dto.response.BookingResponse;
import org.foodie_tour.modules.booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {

    @Mapping(target = "schedule", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "bookingLogs", ignore = true)
    @Mapping(target = "bookingTransactions", ignore = true)
    Booking toBooking(BookingCreateRequest bookingCreateRequest);

    @Mapping(target = "schedule", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "bookingLogs", ignore = true)
    @Mapping(target = "bookingTransactions", ignore = true)
    Booking toEntity(CustomBookingRequest request);

    @Mapping(target = "scheduleId", source = "schedule.scheduleId")
    @Mapping(target = "tourId", source = "tour.tourId")
    @Mapping(target = "routeId", source = "route.routeId")
    @Mapping(target = "departureTime", source = "schedule.departureAt")
    BookingResponse toResponse(Booking booking);
}