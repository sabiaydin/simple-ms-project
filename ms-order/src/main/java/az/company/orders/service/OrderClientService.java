package az.company.orders.service;

import az.company.orders.client.PaymentClient;
import az.company.orders.client.ProductClient;
import az.company.orders.exception.PaymentServiceException;
import az.company.orders.exception.ProductServiceException;
import az.company.orders.model.request.PaymentRequest;
import az.company.orders.model.response.PaymentResponse;
import az.company.orders.model.response.ProductResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderClientService {

    ProductClient productClient;
    PaymentClient paymentClient;

    @CircuitBreaker(
            name = "productService",
            fallbackMethod = "getProductFallback"
    )
    public ProductResponse getProduct(Long productId) {
        return productClient.getProduct(productId);
    }

    private ProductResponse getProductFallback(
            Long productId,
            Throwable throwable) {

        throw new ProductServiceException(
                "Product Service is currently unavailable"
        );
    }

    @CircuitBreaker(
            name = "productService",
            fallbackMethod = "reduceQuantityFallback"
    )
    public void reduceQuantity(Long productId, Integer quantity) {

        productClient.reduceQuantity(productId, quantity);
    }

    private void reduceQuantityFallback(
            Long productId,
            Integer quantity,
            Throwable throwable) {

        throw new ProductServiceException(
                "Product Service is currently unavailable"
        );
    }

    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "payFallback"
    )
    public PaymentResponse pay(PaymentRequest request) {

        return paymentClient.pay(request);
    }

    private PaymentResponse payFallback(
            PaymentRequest request,
            Throwable throwable) {

        throw new PaymentServiceException(
                "Payment Service is currently unavailable"
        );
    }

    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "getPaymentFallback"
    )
    public PaymentResponse getPayment(Long orderId) {

        return paymentClient.getPaymentByOrderId(orderId);
    }

    private PaymentResponse getPaymentFallback(
            Long orderId,
            Throwable throwable) {

        throw new PaymentServiceException(
                "Payment Service is currently unavailable"
        );
    }
}
