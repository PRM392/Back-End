package org.foodie_tour.modules.booking.service;

import org.foodie_tour.modules.booking.dto.request.CustomBookingRequest;
import org.foodie_tour.modules.booking.dto.response.BookingResponse;

public interface CustomTourService {
    BookingResponse createCustomBooking(CustomBookingRequest request);
}
