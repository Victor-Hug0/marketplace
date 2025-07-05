package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.Dto.CreateCustomerRequestDTO;
import br.com.victor.Marketplace.Dto.CustomerResponseDTO;
import br.com.victor.Marketplace.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CreateCustomerRequestDTO dto) {
        CustomerResponseDTO responseDTO = customerService.createCustomer(dto);
        URI location = URI.create("/customer/" + responseDTO.id());
        return ResponseEntity.created(location).body(responseDTO);
    }
}
