package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.ShippingMethod;

public record OrderShippingInfoRequestDTO(
        ShippingMethod shippingMethod
) {
}
