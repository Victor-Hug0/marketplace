package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.*;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.customer.Customer;
import br.com.victor.Marketplace.exception.ResourceNotFoundException;
import br.com.victor.Marketplace.exception.ShippingAddressMissingInfoException;
import br.com.victor.Marketplace.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerAddressesService customerAddressesService;
    private final RestClient.Builder restClientBuilder;
    private final AddressPersistenceService addressPersistenceService;

    public AddressService(AddressRepository addressRepository, CustomerAddressesService customerAddressesService, RestClient.Builder restClientBuilder, AddressPersistenceService addressPersistenceService) {
        this.addressRepository = addressRepository;
        this.customerAddressesService = customerAddressesService;
        this.restClientBuilder = restClientBuilder;
        this.addressPersistenceService = addressPersistenceService;
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
            Address newAddress = createAddress(dto.newAddress());

            if (dto.saveNewAddressForFutureUse().equals(Boolean.TRUE)) {
                customerAddressesService.createCustomerAddresses(customer, newAddress);
            }

            return newAddress;
        }

        throw new ShippingAddressMissingInfoException("Address information is missing. Provide either an existing address ID or new address data.");
    }

    public Address createAddress(CreateAddressViaCepRequestDTO dto) {
        AddessViaCepResponseDTO addessViaCepResponseDTO = fetchAddressFromViaCep(dto.zipCode());
        return addressPersistenceService.saveNewAddress(dto, addessViaCepResponseDTO);
    }

    private AddessViaCepResponseDTO fetchAddressFromViaCep(String zipCode) {
        RestClient restClient = restClientBuilder.baseUrl("https://viacep.com.br/ws/").build();
        try {
            AddessViaCepResponseDTO response = restClient.get()
                    .uri(zipCode + "/json/")
                    .retrieve()
                    .body(AddessViaCepResponseDTO.class);

            if (response == null || response.estado() == null) {
                throw new ResourceNotFoundException("Address not found with zip code: " + zipCode);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar a API ViaCep: " + e.getMessage(), e);
        }
    }

}
