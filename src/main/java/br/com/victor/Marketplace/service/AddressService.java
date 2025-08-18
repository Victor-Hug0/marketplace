package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.*;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.customer.Customer;
import br.com.victor.Marketplace.exception.ResourceNotFoundException;
import br.com.victor.Marketplace.exception.ShippingAddressMissingInfoException;
import br.com.victor.Marketplace.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerAddressesService customerAddressesService;
    private final RestClient.Builder restClientBuilder;

    public AddressService(AddressRepository addressRepository, CustomerAddressesService customerAddressesService, RestClient.Builder restClientBuilder) {
        this.addressRepository = addressRepository;
        this.customerAddressesService = customerAddressesService;
        this.restClientBuilder = restClientBuilder;
    }

    @Transactional
    public Address createAddress(CreateAddressRequestDTO addressRequestDTO) {
        Address address = instantiateAddress(addressRequestDTO);

        addressRepository.save(address);

        return address;
    }

    public List<Address> findByCustomerId(UUID customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    public Address findAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId));
    }

    public Address getOrCreateAddress(ShippingAddressRequestDTO dto, Customer customer) {

        if (dto.existentAddressId() != null) {
            return findAddressById(dto.existentAddressId());
        }

        if (dto.newAddress() != null) {
            Address newAddress = instantiateAddress(dto.newAddress());

            if (dto.saveNewAddressForFutureUse().equals(Boolean.TRUE)) {
                customerAddressesService.createCustomerAddresses(customer, newAddress);
                addressRepository.save(newAddress);
            }

            return newAddress;
        }

        throw new ShippingAddressMissingInfoException("Address information is missing. Provide either an existing address ID or new address data.");
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

    public Address createAddressByZipCodeWithExternalAPI(CreateAddressViaCepRequestDTO dto) {
        RestClient restClient = restClientBuilder.baseUrl("https://viacep.com.br/ws/").build();

        String zipCode = dto.zipCode();

        try {
            AddessViaCepResponseDTO addessViaCepResponseDTO = restClient.get()
                    .uri(zipCode + "/json/")
                    .header("Accept", "application/json")
                    .retrieve()
                    .body(AddessViaCepResponseDTO.class);

            if (addessViaCepResponseDTO == null || addessViaCepResponseDTO.estado() == null) {
                throw new ResourceNotFoundException("Address not found with zip code " + zipCode);
            }

            Address address = new Address(
                    dto.zipCode(),
                    addessViaCepResponseDTO.estado(),
                    addessViaCepResponseDTO.localidade(),
                    addessViaCepResponseDTO.bairro(),
                    addessViaCepResponseDTO.logradouro(),
                    dto.number(),
                    addessViaCepResponseDTO.regiao()
            );

            if (dto.complement() != null) {
                address.setComplement(dto.complement());
            }

            addressRepository.save(address);

            return address;
        } catch (Exception e) {
            throw new RuntimeException("Error to call ViaCep API: " + e);
        }
    }
}
