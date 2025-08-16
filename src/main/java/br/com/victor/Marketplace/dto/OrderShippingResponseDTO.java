package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.ShippingMethod;
import br.com.victor.Marketplace.entity.enums.ShippingStatus;
import br.com.victor.Marketplace.entity.order.Shipping;

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

    public static OrderShippingResponseDTO entityFromDTO(Shipping shipping) {

        AddressResponseDTO shippingAddress = AddressResponseDTO.entityFromDTO(shipping.getShippingAddress());

        return new OrderShippingResponseDTO(
                shipping.getId(),
                shippingAddress,
                shipping.getTrackingCode(),
                shipping.getShippingCost(),
                shipping.getShippingMethod(),
                shipping.getStatus(),
                shipping.getEstimatedDeliveryDate(),
                shipping.getDeliveredDate()
        );
    }
}
