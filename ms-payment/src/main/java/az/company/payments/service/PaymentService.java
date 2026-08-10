package az.company.payments.service;

import az.company.payments.model.request.PaymentRequest;
import az.company.payments.model.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse pay(PaymentRequest request);
    PaymentResponse getPaymentByOrderId(Long orderId);
}
