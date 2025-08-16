package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.CreateAddressRequestDTO;
import br.com.victor.Marketplace.dto.ShippingAddressRequestDTO;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.customer.Customer;
import br.com.victor.Marketplace.exception.ResourceNotFoundException;
import br.com.victor.Marketplace.exception.ShippingAddressMissingInfoException;
import br.com.victor.Marketplace.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerAddressesService customerAddressesService;

    public AddressService(AddressRepository addressRepository, CustomerAddressesService customerAddressesService) {
        this.addressRepository = addressRepository;
        this.customerAddressesService = customerAddressesService;
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
                dto.number()
        );

        if (dto.complement() != null) {
            address.setComplement(dto.complement());
        }

        return address;
    }
}
