package az.company.payments.service.impl;

import az.company.payments.dao.entity.PaymentEntity;
import az.company.payments.dao.repository.PaymentRepository;
import az.company.payments.exception.PaymentNotFoundException;
import az.company.payments.mapper.PaymentMapper;
import az.company.payments.model.request.PaymentRequest;
import az.company.payments.model.response.PaymentResponse;
import az.company.payments.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentServiceImpl implements PaymentService {
    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;

    @Override
    public PaymentResponse pay(PaymentRequest request) {
        PaymentEntity payment = paymentMapper.toEntity(request);
        payment.setReferenceNumber(UUID.randomUUID().toString());
        paymentRepository.save(payment);
        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        PaymentEntity payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() ->
                        new PaymentNotFoundException("Payment not found"));

        return paymentMapper.toResponse(payment);

    }
}
