package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.store.StoreOwnerLegalPerson;

public record StoreOwnerLegalPersonResponseDTO(
        Long id,
        String companyName,
        String fantasyName,
        String companyRegistrationNumber,
        String email,
        String phoneNumber,
        AddressResponseDTO address
) {

    public static StoreOwnerLegalPersonResponseDTO entityFromDTO(StoreOwnerLegalPerson storeOwnerLegalPerson) {

        AddressResponseDTO addressResponseDTO = AddressResponseDTO.entityFromDTO(storeOwnerLegalPerson.getAddress());

        return new StoreOwnerLegalPersonResponseDTO(
                storeOwnerLegalPerson.getId(),
                storeOwnerLegalPerson.getCompanyName(),
                storeOwnerLegalPerson.getFantasyName(),
                storeOwnerLegalPerson.getCompanyRegistrationNumber(),
                storeOwnerLegalPerson.getEmail(),
                storeOwnerLegalPerson.getPhoneNumber(),
                addressResponseDTO
        );
    }
}
