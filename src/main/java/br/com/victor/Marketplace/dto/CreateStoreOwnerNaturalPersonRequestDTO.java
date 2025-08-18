package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateStoreOwnerNaturalPersonRequestDTO(
        @NotEmpty(message = "Field firstName cannot be null.")
        String firstName,
        @NotEmpty(message = "Field lastName cannot be null.")
        String lastName,
        @NotEmpty(message = "Field ssn cannot be null.")
        String ssn,
        @NotNull(message = "Field ssn cannot be null.")
        LocalDate birthDate,
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
        CreateAddressViaCepRequestDTO address
        ) {
}
