package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.store.CreateStoreRequestDTO;
import br.com.victor.Marketplace.dto.store.StoreResponseDTO;
import br.com.victor.Marketplace.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping()
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody @Valid CreateStoreRequestDTO dto) {

        StoreResponseDTO storeResponseDTO = storeService.createStore(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(storeResponseDTO.id()).toUri();

        return ResponseEntity.created(location).body(storeResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> findStoreById(@PathVariable(name = "id") Long id) {
        StoreResponseDTO storeResponseDTO = storeService.getStoreById(id);

        return ResponseEntity.ok(storeResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<StoreResponseDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<StoreResponseDTO> storeResponseDTOS = storeService.getAllStores(pageable);

        return ResponseEntity.ok(storeResponseDTOS);
    }
}
