package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.address.CreateAddressViaCepRequestDTO;
import br.com.victor.Marketplace.dto.customer.CreateCustomerRequestDTO;
import br.com.victor.Marketplace.dto.customer.CustomerResponseDTO;
import br.com.victor.Marketplace.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CreateCustomerRequestDTO dto) {
        CustomerResponseDTO responseDTO = customerService.createCustomer(dto);
        URI location = URI.create("api/v1/customer/" + responseDTO.id());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable UUID id) {
        CustomerResponseDTO responseDTO = customerService.getCustomerResponseDTOFromId(id);
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

    @PostMapping("/{id}/address")
    public ResponseEntity<CustomerResponseDTO> vinculateAddressToCustomer(@PathVariable(name = "id") UUID id, @RequestBody CreateAddressViaCepRequestDTO dto) {
        CustomerResponseDTO responseDTO = customerService.createCustomerAddress(dto, id);
        return ResponseEntity.ok(responseDTO);
    }
}
