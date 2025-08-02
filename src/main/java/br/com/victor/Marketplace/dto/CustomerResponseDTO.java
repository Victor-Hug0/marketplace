package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.Customer;
import br.com.victor.Marketplace.entity.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Gender gender,
        LocalDate birthDate,
        LocalDateTime createdAt
) {

    public static CustomerResponseDTO entityFromDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getGender(),
                customer.getBirthDate(),
                customer.getCreatedAt()
        );
    }
}
