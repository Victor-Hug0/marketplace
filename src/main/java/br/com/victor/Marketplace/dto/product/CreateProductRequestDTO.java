package br.com.victor.Marketplace.dto.product;

import br.com.victor.Marketplace.dto.sku.CreateSkuRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProductRequestDTO(
        @NotEmpty(message = "Field name can't be empty or null!")
        String name,
        @NotEmpty(message = "Field description can't be empty or null!")
        String description,
        @NotNull(message = "Field storeId can't be null!")
        Long storeId,
        @NotNull(message = "Field brandId can't be null!")
        Long brandId,
        @NotNull(message = "Field skus can't be null!")
        List<CreateSkuRequestDTO> skus,
        List<Integer> categoriesId
) {
}
