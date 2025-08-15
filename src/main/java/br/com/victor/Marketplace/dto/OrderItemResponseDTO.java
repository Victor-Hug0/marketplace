package br.com.victor.Marketplace.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        SkuResponseDTO sku,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal marketplaceFee,
        BigDecimal subTotal
) {
}
