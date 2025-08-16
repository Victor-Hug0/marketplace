package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.ShippingMethod;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateOrderShippingRequestDTO(
        @NotNull(message = "Field shippingCost can't be null!")
        BigDecimal shippingCost,
        @NotNull(message = "Field shippingMethod can't be null!")
        ShippingMethod shippingMethod,
        @NotNull(message = "Field estimatedDeliveryDate can't be null!")
        LocalDateTime estimatedDeliveryDate,
        @NotNull(message = "Field address can't be null!")
        ShippingAddressRequestDTO shippingAddress
) {
}
