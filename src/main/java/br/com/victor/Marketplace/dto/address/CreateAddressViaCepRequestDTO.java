package br.com.victor.Marketplace.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateAddressViaCepRequestDTO(
        @JsonProperty(value = "zip_code")
        String zipCode,
        String number,
        String complement
) {
}
