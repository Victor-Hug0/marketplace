package br.com.victor.Marketplace.dto.orderItem;

import jakarta.validation.constraints.NotNull;

public record CreateOrderItemRequestDTO(
        @NotNull(message = "Field skuId can't be null!")
        Long skuId,
        @NotNull(message = "Field quantity can't be null!")
        Integer quantity
) {
}
