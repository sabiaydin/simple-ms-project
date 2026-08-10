package az.company.orders.client;

import az.company.orders.config.FeignConfig;
import az.company.orders.model.request.PaymentRequest;
import az.company.orders.model.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ms-payment",
        configuration = FeignConfig.class
)
public interface PaymentClient {

    @PostMapping("/api/payments")
    PaymentResponse pay(@RequestBody PaymentRequest request);

    @GetMapping("/api/payments/order/{orderId}")
    PaymentResponse getPaymentByOrderId(@PathVariable Long orderId);

}
