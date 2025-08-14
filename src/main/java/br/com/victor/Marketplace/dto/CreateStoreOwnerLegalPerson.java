package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateStoreOwnerLegalPerson(
        String companyName,
        String fantasyName,
        String companyRegistrationNumber,
        @Email(message = "E-mail format invalid.")
        @NotEmpty(message = "Field email cannot be null.")
        String email,
        @NotEmpty(message = "Field password cannot be null.")
        @Size(min = 8)
        String password,
        @NotEmpty(message = "Field passwordConfirmation cannot be null.")
        @Size(min = 8)
        String passwordConfirmation,
        @NotEmpty(message = "Field phoneNumber cannot be null.")
        @Size(min = 11, max = 11, message = "Field phone must be 11 numbers, including DDD.")
        String phoneNumber,
        @NotNull(message = "Field gender cannot be null.")
        Gender gender,
        @NotNull(message = "Field address cannot be null.")
        CreateAddressRequestDTO address
) {
}
