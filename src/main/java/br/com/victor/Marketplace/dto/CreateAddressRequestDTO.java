package br.com.victor.Marketplace.dto;

import jakarta.validation.constraints.Pattern;

public record CreateAddressRequestDTO(
        @Pattern(regexp = "^\\d{5}-\\d{3}$")
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String complement,
        String number
) {
}
