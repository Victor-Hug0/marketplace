package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.order.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, UUID> {
}
