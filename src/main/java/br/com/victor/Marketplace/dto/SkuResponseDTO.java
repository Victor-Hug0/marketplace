package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.product.Sku;
import br.com.victor.Marketplace.entity.product.SkuAttributeDecimal;
import br.com.victor.Marketplace.entity.product.SkuAttributeInteger;
import br.com.victor.Marketplace.entity.product.SkuAttributeString;

import java.math.BigDecimal;
import java.util.List;

public record SkuResponseDTO(
        Long id,
        String skuCode,
        BigDecimal price,
        Integer stock,
        List<SkuAttributeDecimal> decimalAttributes,
        List<SkuAttributeInteger> integerAttributes,
        List<SkuAttributeString> stringAttributes
) {

    public static SkuResponseDTO entityFromDTO(Sku sku) {
        return new SkuResponseDTO(
                sku.getId(),
                sku.getSkuCode(),
                sku.getPrice(),
                sku.getStock(),
                sku.getSkuAttributesDecimal(),
                sku.getSkuAttributesInteger(),
                sku.getSkuAttributesString()
        );
    }
}
