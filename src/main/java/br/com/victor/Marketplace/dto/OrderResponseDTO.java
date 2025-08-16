package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.order.Order;
import br.com.victor.Marketplace.entity.order.OrderItem;
import br.com.victor.Marketplace.entity.order.OrderPayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        CustomerResponseDTO customer,
        OrderShippingResponseDTO orderShipping,
        BigDecimal totalItensAmount,
        BigDecimal taxAmount,
        BigDecimal marketplaceFeeTotal,
        List<OrderItemResponseDTO> orderItems,
        List<PaymentResponseDTO> payments,
        LocalDateTime orderDate
) {

    public static OrderResponseDTO entityFromDTO(Order order) {

        CustomerResponseDTO customer = CustomerResponseDTO.entityFromDTO(order.getCustomer());

        OrderShippingResponseDTO orderShipping = OrderShippingResponseDTO.entityFromDTO(order.getShipping());

        List<OrderItemResponseDTO> orderItemResponseDTOS = new ArrayList<>();

        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemResponseDTO orderItemResponseDTO = OrderItemResponseDTO.entityFromDTO(orderItem);
            orderItemResponseDTOS.add(orderItemResponseDTO);
        }

        List<PaymentResponseDTO> paymentResponseDTOS = new ArrayList<>();

        for (OrderPayment orderPayment : order.getPayments()) {
            PaymentResponseDTO paymentResponseDTO = PaymentResponseDTO.entityFromDTO(orderPayment);
            paymentResponseDTOS.add(paymentResponseDTO);
        }

        return new OrderResponseDTO(
                order.getId(),
                customer,
                orderShipping,
                order.getTotalItensAmount(),
                order.getTaxAmount(),
                order.getMarketplaceFeeTotal(),
                orderItemResponseDTOS,
                paymentResponseDTOS,
                order.getOrderDate()
        );
    }
}
