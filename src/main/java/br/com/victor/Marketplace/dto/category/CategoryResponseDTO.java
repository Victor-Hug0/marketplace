package br.com.victor.Marketplace.dto.category;

import br.com.victor.Marketplace.entity.product.Category;

public record CategoryResponseDTO(
        Long id,
        String name
) {

    public static CategoryResponseDTO entityFromDTO(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
}
