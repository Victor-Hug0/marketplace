package br.com.victor.Marketplace.dto.administrator;

import br.com.victor.Marketplace.entity.admin.AdminRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateAdministratorRequestDTO(
        @NotEmpty(message = "Field firstName cannot be null.")
        String fistName,
        @NotEmpty(message = "Field lastName cannot be null.")
        String lastName,
        @NotEmpty(message = "Field email cannot be null.")
        @Email(message = "E-mail format invalid.")
        String email,
        @NotEmpty(message = "Field password cannot be null.")
        String password,
        @NotEmpty(message = "Field passwordConfirmation cannot be null.")
        String passwordConfirmation,
        @NotNull(message = "Field role cannot be null.")
        AdminRole role
) {
}
