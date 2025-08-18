package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.AddessViaCepResponseDTO;
import br.com.victor.Marketplace.dto.CreateAddressRequestDTO;
import br.com.victor.Marketplace.dto.CreateAddressViaCepRequestDTO;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressPersistenceService {

    private final AddressRepository addressRepository;

    public AddressPersistenceService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Address saveNewAddress(CreateAddressViaCepRequestDTO addressRequestDTO, AddessViaCepResponseDTO addessViaCepResponseDTO) {
        Address address = instantiateAddress(addessViaCepResponseDTO, addressRequestDTO);
        return addressRepository.save(address);
    }

    @Transactional
    public Address saveNewAddress(CreateAddressRequestDTO addressRequestDTO) {
        Address address = instantiateAddress(addressRequestDTO);
        return addressRepository.save(address);
    }

    private Address instantiateAddress(AddessViaCepResponseDTO dto, CreateAddressViaCepRequestDTO addressViaCepRequestDTO) {
        Address address = new Address(
                addressViaCepRequestDTO.zipCode(),
                dto.estado(),
                dto.localidade(),
                dto.bairro(),
                dto.logradouro(),
                addressViaCepRequestDTO.number(),
                dto.regiao()
        );

        if (dto.complemento() != null) {
            address.setComplement(dto.complemento());
        }
        return address;
    }

    private Address instantiateAddress(CreateAddressRequestDTO dto) {
        Address address = new Address(
                dto.zipCode(),
                dto.state(),
                dto.city(),
                dto.neighborhood(),
                dto.street(),
                dto.number(),
                dto.region()
        );

        if (dto.complement() != null) {
            address.setComplement(dto.complement());
        }

        return address;
    }
}
