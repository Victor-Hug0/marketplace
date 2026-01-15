package br.com.victor.Marketplace.dto.administrator;

import br.com.victor.Marketplace.entity.admin.AdminRole;
import br.com.victor.Marketplace.entity.admin.AdminStatus;
import br.com.victor.Marketplace.entity.admin.Administrator;

import java.time.LocalDateTime;

public record AdministratorResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        AdminStatus status,
        AdminRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static AdministratorResponseDTO entityFromDTO(Administrator administrator) {
        return new AdministratorResponseDTO(
                administrator.getId(),
                administrator.getFirstName(),
                administrator.getLastName(),
                administrator.getEmail(),
                administrator.getStatus(),
                administrator.getRole(),
                administrator.getCreatedAt(),
                administrator.getUpdatedAt()
        );
    }
}
