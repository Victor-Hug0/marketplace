package br.com.victor.Marketplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStoreRequestDTO(
        @NotBlank(message = "Field companyName can't be empty or null!")
        String companyName,
        @NotBlank(message = "Field fantasyName can't be empty or null!")
        String fantasyName,
        @NotBlank(message = "Field companyRegistrationNumber can't be empty or null!")
        String companyRegistrationNumber,
        @NotBlank(message = "Field biography can't be empty or null!")
        String biography,
        @NotBlank(message = "Field contactPhoneNumber can't be empty or null!")
        String contactPhoneNumber,
        @NotBlank(message = "Field contactEmail can't be empty or null!")
        @Email(message = "E-mail format not valid!")
        String contactEmail,
        @NotNull(message = "Field ownerId can't be null!")
        Long ownerId
) {
}
