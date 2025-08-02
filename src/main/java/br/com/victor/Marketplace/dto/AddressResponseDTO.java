package br.com.victor.Marketplace.dto;

public record AddressResponseDTO(
        Long id,
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String complement,
        String number
) {
}
