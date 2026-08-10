package az.company.apigateway.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    @RequestMapping("/fallback/product")
    public ResponseEntity<Map<String, Object>> productFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "status", 503,
                        "message", "Product Service is currently unavailable",
                        "service", "ms-product"
                ));
    }

    @RequestMapping("/fallback/order")
    public ResponseEntity<Map<String, Object>> orderFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "status", 503,
                        "message", "Order Service is currently unavailable",
                        "service", "ms-order"
                ));
    }

    @RequestMapping("/fallback/payment")
    public ResponseEntity<Map<String, Object>> paymentFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "status", 503,
                        "message", "Payment Service is currently unavailable",
                        "service", "ms-payment"
                ));
    }
}