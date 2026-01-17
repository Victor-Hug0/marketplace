package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.administrator.AdministratorResponseDTO;
import br.com.victor.Marketplace.dto.administrator.CreateAdministratorRequestDTO;
import br.com.victor.Marketplace.service.AdministratorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admins")
public class AdminController {

    private final AdministratorService administratorService;

    public AdminController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping
    public ResponseEntity<AdministratorResponseDTO> create(@RequestBody @Valid CreateAdministratorRequestDTO dto) {

        try {
            AdministratorResponseDTO responseDTO = administratorService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AdministratorResponseDTO> getById(@PathVariable Long id) {
        try {
            AdministratorResponseDTO responseDTO = administratorService.getById(id);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<AdministratorResponseDTO>> getAll(@PageableDefault(sort = "firstName") Pageable pageable) {
        try {
            Page<AdministratorResponseDTO> responseDTOs = administratorService.getAll(pageable);
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> desactivateAdministrator(@PathVariable Long id) {
        try {
            administratorService.desactivateAdministrator(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
