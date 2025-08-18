package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.SkuColor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CreateSkuRequestDTO(
        @NotEmpty(message = "Field skuCode can't be empty or null!")
        String skuCode,
        @NotNull(message = "Field color can't be null!")
        SkuColor color,
        @NotNull(message = "Field width can't be null!")
        Integer width,
        @NotNull(message = "Field height can't be null!")
        Integer height,
        @NotNull(message = "Field length can't be null!")
        Integer length,
        @NotNull(message = "Field weight can't be null!")
        BigDecimal weight,
        @NotNull(message = "Field price can't be null!")
        @Min(value = 3, message = "Price must be at least 3")
        BigDecimal price,
        @NotNull(message = "Field stock can't be null!")
        Integer stock,
        @NotNull(message = "Field attributes can't be null!")
        List<CreateAttributeRequestDTO> attributes
) {
}
