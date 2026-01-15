package br.com.victor.Marketplace.dto.order;

import br.com.victor.Marketplace.dto.shipping.ShippingAddressRequestDTO;
import br.com.victor.Marketplace.dto.orderItem.CreateOrderItemRequestDTO;
import br.com.victor.Marketplace.dto.shipping.OrderShippingInfoRequestDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequestDTO(
        @NotNull(message = "Field customerId can't be null!")
        UUID customerId,
        @NotNull(message = "Field shippingAddress can't be null!")
        ShippingAddressRequestDTO shippingAddress,
        @NotNull(message = "Field orderItems can't be null!")
        List<CreateOrderItemRequestDTO> orderItems,
        @NotNull(message = "Field payment can't be null!")
        CreateOrderPaymentRequestDTO payment,
        @NotNull(message = "Field shippingInfo can't be null!")
        OrderShippingInfoRequestDTO shippingInfo
) {
}
