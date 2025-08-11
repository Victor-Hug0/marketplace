package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.product.Category;
import br.com.victor.Marketplace.entity.product.Product;
import br.com.victor.Marketplace.entity.product.Sku;

import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        List<Category> categories,
        List<Sku> skus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProductResponseDTO entityFromDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategories(),
                product.getSkus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
