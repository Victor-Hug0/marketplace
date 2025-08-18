package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.SkuColor;
import br.com.victor.Marketplace.entity.product.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record SkuResponseDTO(
        Long id,
        String skuCode,
        BigDecimal price,
        SkuColor color,
        Integer width,
        Integer height,
        Integer length,
        BigDecimal weight,
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
                sku.getColor(),
                sku.getWidth(),
                sku.getHeight(),
                sku.getLength(),
                sku.getWeight(),
                availableQuantity,
                sku.getSkuAttributesDecimal(),
                sku.getSkuAttributesInteger(),
                sku.getSkuAttributesString()
        );
    }
}
