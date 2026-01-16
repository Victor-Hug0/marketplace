package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.storeOwner.CreateStoreOwnerLegalPersonRequestDTO;
import br.com.victor.Marketplace.dto.storeOwner.CreateStoreOwnerNaturalPersonRequestDTO;
import br.com.victor.Marketplace.dto.storeOwner.StoreOwnerLegalPersonResponseDTO;
import br.com.victor.Marketplace.dto.storeOwner.StoreOwnerNaturalPersonResponseDTO;
import br.com.victor.Marketplace.service.StoreOwnerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/storeOwners")
public class StoreOwnerController {

    private final StoreOwnerService storeOwnerService;

    public StoreOwnerController(StoreOwnerService storeOwnerService) {
        this.storeOwnerService = storeOwnerService;
    }

    @PostMapping("/createStoreOwnerNaturalPerson")
    public ResponseEntity<StoreOwnerNaturalPersonResponseDTO> createStoreOwnerNaturalPerson(@RequestBody @Valid CreateStoreOwnerNaturalPersonRequestDTO dto){

        StoreOwnerNaturalPersonResponseDTO responseDTO = storeOwnerService.createStoreOwnerNaturalPerson(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @PostMapping("/createStoreOwnerLegalPerson")
    public ResponseEntity<StoreOwnerLegalPersonResponseDTO> createStoreOwnerLegalPerson(@RequestBody @Valid CreateStoreOwnerLegalPersonRequestDTO dto) {
        StoreOwnerLegalPersonResponseDTO responseDTO = storeOwnerService.createStoreOwnerLegalPerson(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }
}
