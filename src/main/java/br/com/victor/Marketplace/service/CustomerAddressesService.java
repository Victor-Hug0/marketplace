package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.entity.Address;
import br.com.victor.Marketplace.entity.customer.Customer;
import br.com.victor.Marketplace.entity.customer.CustomerAddresses;
import br.com.victor.Marketplace.repository.CustomerAddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressesService {

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressesService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    @Transactional
    public void createCustomerAddresses(Customer customer, Address address) {
        CustomerAddresses customerAddresses = new CustomerAddresses(address, customer);
        customer.getCustomerAddresses().add(customerAddresses);
        customerAddressRepository.saveAndFlush(customerAddresses);
    }
}
