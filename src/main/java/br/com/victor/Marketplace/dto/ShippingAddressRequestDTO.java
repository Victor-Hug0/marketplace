package br.com.victor.Marketplace.dto;

import jakarta.validation.constraints.NotNull;

public record ShippingAddressRequestDTO(
        Long existentAddressId,
        CreateAddressViaCepRequestDTO newAddress,
        @NotNull(message = "Field saveNewAddressForFutureUse can't be null!")
        Boolean saveNewAddressForFutureUse
) {
}
