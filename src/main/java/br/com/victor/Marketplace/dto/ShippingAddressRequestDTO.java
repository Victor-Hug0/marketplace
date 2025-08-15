package br.com.victor.Marketplace.dto;

import jakarta.validation.constraints.NotNull;

public record ShippingAddressRequestDTO(
        @NotNull(message = "Field existentAddressId can't be null!")
        Long existentAddressId,
        @NotNull(message = "Field newAddress can't be null!")
        CreateAddressRequestDTO newAddress,
        @NotNull(message = "Field saveNewAddressForFutureUse can't be null!")
        Boolean saveNewAddressForFutureUse
) {
}
