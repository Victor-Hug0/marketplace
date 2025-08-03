package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.CreateAddressRequestDTO;
import br.com.victor.Marketplace.entity.Address;
import br.com.victor.Marketplace.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddessService {

    private final AddressRepository addressRepository;

    public AddessService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(CreateAddressRequestDTO addressRequestDTO) {
        Address address = new Address(
                addressRequestDTO.zipCode(),
                addressRequestDTO.state(),
                addressRequestDTO.city(),
                addressRequestDTO.neighborhood(),
                addressRequestDTO.street(),
                addressRequestDTO.number()
        );

        if (addressRequestDTO.complement() != null) {
            address.setComplement(addressRequestDTO.complement());
        }

        addressRepository.save(address);

        return address;
    }
}
