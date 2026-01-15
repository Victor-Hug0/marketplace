package br.com.victor.Marketplace.dto.store;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateStoreRequestDTO(
        @NotEmpty(message = "Field companyName can't be empty or null!")
        String companyName,
        @NotEmpty(message = "Field fantasyName can't be empty or null!")
        String fantasyName,
        @NotEmpty(message = "Field companyRegistrationNumber can't be empty or null!")
        String companyRegistrationNumber,
        @NotEmpty(message = "Field biography can't be empty or null!")
        String biography,
        @NotEmpty(message = "Field contactPhoneNumber can't be empty or null!")
        String contactPhoneNumber,
        @NotEmpty(message = "Field contactEmail can't be empty or null!")
        @Email(message = "E-mail format not valid!")
        String contactEmail,
        @NotNull(message = "Field ownerId can't be null!")
        Long ownerId
) {
}
