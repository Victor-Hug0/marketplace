package br.com.victor.Marketplace.dto.sku;

import br.com.victor.Marketplace.entity.product.SkuStock;

public record SkuStockResponseDTO(
        Long id,
        Integer availableQuantity
) {

    public static SkuStockResponseDTO entityFromDTO(SkuStock skuStock) {
        return new SkuStockResponseDTO(
                skuStock.getId(),
                skuStock.getAvailableQuantity()
        );
    }
}
