package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.entity.order.OrderPayment;
import br.com.victor.Marketplace.repository.OrderPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderPaymentService {

    private final OrderPaymentRepository orderPaymentRepository;

    public OrderPaymentService(OrderPaymentRepository orderPaymentRepository) {
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Transactional
    public void save(OrderPayment orderPayment) {
        orderPaymentRepository.save(orderPayment);
    }
}
