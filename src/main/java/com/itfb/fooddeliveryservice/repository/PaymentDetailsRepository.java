package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
}
