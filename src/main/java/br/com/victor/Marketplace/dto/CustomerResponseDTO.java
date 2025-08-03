package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.customer.Customer;
import br.com.victor.Marketplace.entity.customer.CustomerAddresses;
import br.com.victor.Marketplace.entity.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Gender gender,
        LocalDate birthDate,
        List<AddressResponseDTO> address,
        LocalDateTime createdAt
) {

    public static CustomerResponseDTO entityFromDTO(Customer customer) {
        List<AddressResponseDTO> addresses = customer.getCustomerAddresses().stream()
                .map(CustomerAddresses::getAddress)
                .map(AddressResponseDTO::fromEntity)
                .toList();

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getGender(),
                customer.getBirthDate(),
                addresses,
                customer.getCreatedAt()
        );
    }
}
