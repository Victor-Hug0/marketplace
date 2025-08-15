package br.com.victor.Marketplace.dto;

public record ShippingAddressRequestDTO(
        Long existentAddressId,
        CreateAddressRequestDTO newAddress,
        Boolean saveNewAddressForFutureUse
) {
}
