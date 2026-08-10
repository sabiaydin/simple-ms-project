package az.company.payments.dao.repository;

import az.company.payments.dao.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findByOrderId(Long orderId);
}
