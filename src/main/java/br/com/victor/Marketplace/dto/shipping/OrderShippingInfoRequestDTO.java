package br.com.victor.Marketplace.dto.shipping;

import br.com.victor.Marketplace.entity.enums.ShippingMethod;
import jakarta.validation.constraints.NotNull;

public record OrderShippingInfoRequestDTO(
        @NotNull(message = "Field shippingMethod can't be null!")
        ShippingMethod shippingMethod
) {
}
