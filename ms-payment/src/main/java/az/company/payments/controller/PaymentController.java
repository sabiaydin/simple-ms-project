package az.company.payments.controller;

import az.company.payments.model.request.PaymentRequest;
import az.company.payments.model.response.PaymentResponse;
import az.company.payments.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(
            @Valid @RequestBody PaymentRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.pay(request));
    }

    @GetMapping("/order/{orderId}")
    public PaymentResponse getPayment(
            @PathVariable Long orderId){

        return service.getPaymentByOrderId(orderId);
    }

}