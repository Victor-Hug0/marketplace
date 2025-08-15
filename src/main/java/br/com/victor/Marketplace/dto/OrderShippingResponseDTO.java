package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.ShippingMethod;
import br.com.victor.Marketplace.entity.enums.ShippingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderShippingResponseDTO(
        Long id,
        AddressResponseDTO shippingAddress,
        String trackingCode,
        BigDecimal shippingCost,
        ShippingMethod shippingMethod,
        ShippingStatus status,
        LocalDateTime estimatedDeliveryDate,
        LocalDateTime deliveredDate
) {
}
