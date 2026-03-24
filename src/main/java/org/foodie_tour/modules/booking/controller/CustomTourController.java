package org.foodie_tour.modules.booking.controller;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.modules.booking.dto.request.CustomBookingRequest;
import org.foodie_tour.modules.booking.dto.response.BookingResponse;
import org.foodie_tour.modules.booking.service.CustomTourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking/custom")
@RequiredArgsConstructor
public class CustomTourController {

    private final CustomTourService customTourService;

    @PostMapping
    public ResponseEntity<BookingResponse> createCustomBooking(@RequestBody CustomBookingRequest request) {
        BookingResponse response = customTourService.createCustomBooking(request);
        return ResponseEntity.ok(response);
    }
}
