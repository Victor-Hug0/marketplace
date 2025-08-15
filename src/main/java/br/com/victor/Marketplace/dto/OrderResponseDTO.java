package br.com.victor.Marketplace.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        CustomerResponseDTO customer,
        OrderShippingResponseDTO orderShipping,
        BigDecimal totalItensAmount,
        BigDecimal taxAmount,
        List<OrderItemResponseDTO> orderItems,
        List<PaymentResponseDTO> payments,
        LocalDateTime orderDate
) {
}
