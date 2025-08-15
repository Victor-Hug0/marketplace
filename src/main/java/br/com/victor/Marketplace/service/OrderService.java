package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.CreateOrderRequestDTO;
import br.com.victor.Marketplace.dto.OrderResponseDTO;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.customer.Customer;
import br.com.victor.Marketplace.exception.EmptyOrderItemsException;
import br.com.victor.Marketplace.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final AddressService addressService;

    @Value("${marketplace.fee}")
    private BigDecimal MARKETPLACE_FEE;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, AddressService addressService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @Transactional
    public OrderResponseDTO createOrder(CreateOrderRequestDTO dto) {

        Customer customer = customerService.getCustomerById(dto.customerId());
        Address address = addressService.getOrCreateAddress(dto.shippingAddress());

        if (dto.orderItems().isEmpty()) {
            throw new EmptyOrderItemsException("Order items cannot be empty");
        }
    }
}
