package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.CreateCustomerRequestDTO;
import br.com.victor.Marketplace.dto.CustomerResponseDTO;
import br.com.victor.Marketplace.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable UUID id) {
        CustomerResponseDTO responseDTO = customerService.getCustomerFromId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomerFromId(id);
        return ResponseEntity.noContent().build();
    }
}
