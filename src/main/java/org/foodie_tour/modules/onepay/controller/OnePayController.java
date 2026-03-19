package org.foodie_tour.modules.onepay.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.foodie_tour.modules.onepay.service.OnePayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onepay")
public class OnePayController {

    private final OnePayService onePayService;

    @PostMapping("/generate-payment-url")
    public ResponseEntity<String> generatePaymentUrl(@RequestBody long bookingId, HttpServletRequest request) {
        return ResponseEntity.ok(onePayService.generatePaymentUrl(bookingId, request));
    }

    @GetMapping("/result")
    public RedirectView handleCallback(@RequestParam Map<String, String> params) {
        RedirectView redirectView = new RedirectView();
        String url = onePayService.processPaymentResponse(params);
        redirectView.setUrl(url);
        return redirectView;
    }
}
