package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.product.*;

import java.math.BigDecimal;
import java.util.List;

public record SkuResponseDTO(
        Long id,
        String skuCode,
        BigDecimal price,
        Integer avaliableQuantity,
        List<SkuAttributeDecimal> decimalAttributes,
        List<SkuAttributeInteger> integerAttributes,
        List<SkuAttributeString> stringAttributes
) {

    public static SkuResponseDTO entityFromDTO(Sku sku) {

        Integer availableQuantity = (sku.getSkuStock() != null ? sku.getSkuStock().getAvailableQuantity() : null);

        return new SkuResponseDTO(
                sku.getId(),
                sku.getSkuCode(),
                sku.getPrice(),
                availableQuantity,
                sku.getSkuAttributesDecimal(),
                sku.getSkuAttributesInteger(),
                sku.getSkuAttributesString()
        );
    }
}
