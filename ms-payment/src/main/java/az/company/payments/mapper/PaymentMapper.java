package az.company.payments.mapper;

import az.company.payments.dao.entity.PaymentEntity;
import az.company.payments.model.request.PaymentRequest;
import az.company.payments.model.response.PaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentEntity toEntity(PaymentRequest request);

    PaymentResponse toResponse(PaymentEntity entity);

}
