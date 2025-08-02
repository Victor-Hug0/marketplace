package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateCustomerRequestDTO(
        @NotBlank(message = "Field firstName cannot be null.")
        @Size(max = 50, message = "Field firstName must have a maximum of 50 characters.")
        String firstName,
        @NotBlank(message = "Field lastName cannot be null.")
        @Size(max = 50, message = "Field lastName must have a maximum of 50 characters.")
        String lastName,
        @Email(message = "E-mail format invalid.")
        @NotBlank(message = "Field email cannot be null.")
        String email,
        @NotBlank(message = "Field password cannot be null.")
        @Size(min = 8)
        String password,
        @NotBlank(message = "Field passwordConfirmation cannot be null.")
        @Size(min = 8)
        String passwordConfirmation,
        @NotBlank(message = "Field phone cannot be null.")
        @Size(min = 11, max = 11, message = "Field phone must be 11 numbers, including DDD.")
        String phone,
        @NotBlank(message = "Field cpf cannot be null.")
        @Size(min = 11, max = 11, message = "Field cpf must be 11 numbers")
        String cpf,
        @NotNull(message = "Field gender cannot be null.")
        Gender gender,
        @NotNull(message = "Field birthDate cannot be null.")
        LocalDate birthDate
) {
}
