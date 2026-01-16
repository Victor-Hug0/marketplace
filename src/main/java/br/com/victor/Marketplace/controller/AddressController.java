package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.address.CreateAddressViaCepRequestDTO;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping()
    public ResponseEntity<Address> createAddress(@RequestBody CreateAddressViaCepRequestDTO dto) {

        Address address = addressService.createAddress(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();

        return ResponseEntity.created(location).body(address);
    }
}
