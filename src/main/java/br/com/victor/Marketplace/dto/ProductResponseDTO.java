package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.product.Product;


import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        List<CategoryResponseDTO> categories,
        List<SkuResponseDTO> skus,
        LocalDateTime createdAt,
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
                categoryResponseDTOS,
                skuResponseDTOS,
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
