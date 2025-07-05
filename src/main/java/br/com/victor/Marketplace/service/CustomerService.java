package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.Dto.CreateCustomerRequestDTO;
import br.com.victor.Marketplace.Dto.CustomerResponseDTO;
import br.com.victor.Marketplace.entity.Customer;
import br.com.victor.Marketplace.exception.CpfAlreadyExistsException;
import br.com.victor.Marketplace.exception.EmailAlreadyExistsException;
import br.com.victor.Marketplace.exception.InvalidPasswordException;
import br.com.victor.Marketplace.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
