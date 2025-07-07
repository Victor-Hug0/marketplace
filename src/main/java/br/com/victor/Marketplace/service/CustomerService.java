package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.Dto.CreateCustomerRequestDTO;
import br.com.victor.Marketplace.Dto.CustomerResponseDTO;
import br.com.victor.Marketplace.entity.Customer;
import br.com.victor.Marketplace.exception.CpfAlreadyExistsException;
import br.com.victor.Marketplace.exception.CustomerNotFoundException;
import br.com.victor.Marketplace.exception.EmailAlreadyExistsException;
import br.com.victor.Marketplace.exception.InvalidPasswordException;
import br.com.victor.Marketplace.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO createCustomer(CreateCustomerRequestDTO dto) {

        if (!dto.password().equals(dto.passwordConfirmation())) {
            throw new InvalidPasswordException("Passwords do not match");
        }

        if (customerRepository.existsByCpf(dto.cpf())) {
            throw new CpfAlreadyExistsException("Cpf already exists in database");
        }

        if (customerRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("Email already exists in database");
        }

        Customer customer = new Customer(
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.password(),
                dto.phone(),
                dto.cpf(),
                dto.gender(),
                dto.birthDate(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        customerRepository.save(customer);

        return CustomerResponseDTO.entityFromDTO(customer);
    }

    public CustomerResponseDTO getCustomerFromId(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + "not found."));
        return CustomerResponseDTO.entityFromDTO(customer);
    }

    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(CustomerResponseDTO::entityFromDTO);
    }

    public void deleteCustomerFromId(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found.");
        }

        customerRepository.deleteById(id);
    }
}
