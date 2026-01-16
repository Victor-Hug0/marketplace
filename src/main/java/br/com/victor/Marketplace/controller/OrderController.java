package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.order.CreateOrderRequestDTO;
import br.com.victor.Marketplace.dto.order.OrderResponseDTO;
import br.com.victor.Marketplace.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid CreateOrderRequestDTO dto) {

        OrderResponseDTO orderResponseDTO = orderService.createOrder(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{orderId}").buildAndExpand(orderResponseDTO.id()).toUri();

        return ResponseEntity.created(location).body(orderResponseDTO);
    }
}
