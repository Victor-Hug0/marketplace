package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.Gender;
import br.com.victor.Marketplace.entity.store.StoreOwnerNaturalPerson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record StoreOwnerNaturalPersonResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String ssn,
        LocalDate birthDate,
        String email,
        String phoneNumber,
        Gender gender,
        List<StoreResponseDTO> stores,
        AddressResponseDTO address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static StoreOwnerNaturalPersonResponseDTO entityFromDTO(StoreOwnerNaturalPerson storeOwnerNaturalPerson) {

        List<StoreResponseDTO> stores = storeOwnerNaturalPerson.getStores().stream()
                .map(StoreResponseDTO::entityFromDTO)
                .toList();

        AddressResponseDTO addressResponseDTO = AddressResponseDTO.entityFromDTO(storeOwnerNaturalPerson.getAddress());

        return new StoreOwnerNaturalPersonResponseDTO(
                storeOwnerNaturalPerson.getId(),
                storeOwnerNaturalPerson.getFirstName(),
                storeOwnerNaturalPerson.getLastName(),
                storeOwnerNaturalPerson.getSsn(),
                storeOwnerNaturalPerson.getBirthDate(),
                storeOwnerNaturalPerson.getEmail(),
                storeOwnerNaturalPerson.getPhoneNumber(),
                storeOwnerNaturalPerson.getGender(),
                stores,
                addressResponseDTO,
                storeOwnerNaturalPerson.getCreatedAt(),
                storeOwnerNaturalPerson.getUpdatedAt()
        );
    }
}
