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
    @Mapping(target = "tourName", source = "tour.tourName")
    @Mapping(target = "routeId", source = "route.routeId")
    @Mapping(target = "departureTime", source = "schedule.departureAt")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "customerEmail", source = "email")
    @Mapping(target = "customerPhone", source = "phone")
    @Mapping(target = "adultCount", source = "adultCount")
    @Mapping(target = "childrenCount", source = "childrenCount")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    @Mapping(target = "createdAt", source = "createAt")
    @Mapping(target = "updatedAt", source = "updateAt")
    @Mapping(target = "depositAmount", expression = "java(booking.getDeposit() != null && booking.getDeposit() ? (long)(booking.getTotalPrice() * 0.3) : 0L)")
    @Mapping(target = "remainingPercent", expression = "java(booking.getTotalPrice() != null && booking.getTotalPrice() > 0 ? (long)((booking.getRemainingAmount() != null ? booking.getRemainingAmount() : 0L) * 100 / booking.getTotalPrice()) : 0L)")
    @Mapping(target = "numberOfPeople", expression = "java(booking.getAdultCount() + booking.getChildrenCount())")
    @Mapping(target = "paymentStatusText", expression = "java((booking.getRemainingAmount() == null || booking.getRemainingAmount() == 0) ? \"Đã thanh toán đủ\" : \"Đã thanh toán \" + ((booking.getTotalPrice() > 0) ? (booking.getAmountPaid() * 100 / booking.getTotalPrice()) : 0) + \"%\")")
    @Mapping(target = "bookingStatusText", expression = "java((booking.getBookingStatus() == null) ? \"\" : booking.getBookingStatus().name())")
    BookingResponse toResponse(Booking booking);
}