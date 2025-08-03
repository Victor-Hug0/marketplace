package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.address.Address;

public record AddressResponseDTO(
        Long id,
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String complement,
        String number
) {

    public static AddressResponseDTO fromEntity(Address address) {
        return new AddressResponseDTO(
                address.getId(),
                address.getZipCode(),
                address.getState(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getComplement(),
                address.getNumber()
        );
    }
}
