package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.product.Product;
import br.com.victor.Marketplace.entity.product.ProductStatus;


import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        ProductStatus status,
        List<CategoryResponseDTO> categories,
        List<SkuResponseDTO> skus,
        LocalDateTime createdAt,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt
) {

    public static ProductResponseDTO entityFromDTO(Product product) {

        List<CategoryResponseDTO> categoryResponseDTOS = product.getCategories().stream()
                .map(CategoryResponseDTO::entityFromDTO)
                .toList();

        List<SkuResponseDTO> skuResponseDTOS = product.getSkus().stream()
                .map(SkuResponseDTO::entityFromDTO)
                .toList();

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStatus(),
                categoryResponseDTOS,
                skuResponseDTOS,
                product.getCreatedAt(),
                product.getPublishedAt(),
                product.getUpdatedAt()
        );
    }
}
