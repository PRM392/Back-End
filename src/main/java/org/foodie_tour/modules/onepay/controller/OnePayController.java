package org.foodie_tour.modules.onepay.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.foodie_tour.modules.onepay.service.OnePayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onepay")
public class OnePayController {

    private final OnePayService onePayService;

    @PostMapping("/generate-payment-url")
    public ResponseEntity<String> generatePaymentUrl(@RequestBody long bookingId, HttpServletRequest request) {
        String url = onePayService.generatePaymentUrl(bookingId, request);
        System.out.println("DEBUG OnePay Generated URL: " + url);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/result")
    public void handleCallback(@RequestParam Map<String, String> params, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        String result = onePayService.processPaymentResponse(params);
        response.sendRedirect(result);
    }
}
