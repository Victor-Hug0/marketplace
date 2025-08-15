package br.com.victor.Marketplace.dto;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequestDTO(
        UUID customerId,
        ShippingAddressRequestDTO shippingAddress,
        List<CreateOrderItemRequestDTO> orderItems,
        CreateOrderPaymentRequestDTO payment,
        OrderShippingInfoRequestDTO shippingInfo
) {
}
