
package com.ecommerce.payment_service.repository;

import com.ecommerce.payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}