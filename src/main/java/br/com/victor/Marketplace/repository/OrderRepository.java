package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
