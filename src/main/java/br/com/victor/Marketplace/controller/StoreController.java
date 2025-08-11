package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.CreateStoreRequestDTO;
import br.com.victor.Marketplace.dto.StoreResponseDTO;
import br.com.victor.Marketplace.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<StoreResponseDTO> create(@RequestBody @Valid CreateStoreRequestDTO dto) {

        StoreResponseDTO storeResponseDTO = storeService.createStore(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(storeResponseDTO.id()).toUri();

        return ResponseEntity.created(location).body(storeResponseDTO);
    }
}
