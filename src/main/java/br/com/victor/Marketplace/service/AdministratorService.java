package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.administrator.AdministratorResponseDTO;
import br.com.victor.Marketplace.dto.administrator.CreateAdministratorRequestDTO;
import br.com.victor.Marketplace.entity.admin.AdminStatus;
import br.com.victor.Marketplace.entity.admin.Administrator;
import br.com.victor.Marketplace.exception.EmailAlreadyExistsException;
import br.com.victor.Marketplace.exception.InvalidPasswordException;
import br.com.victor.Marketplace.exception.ResourceNotFoundException;
import br.com.victor.Marketplace.repository.AdministratorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public AdministratorResponseDTO create(CreateAdministratorRequestDTO dto) {

        if (!dto.password().equals(dto.passwordConfirmation())) {
            throw new InvalidPasswordException("Passwords don't match");
        }

        if (administratorRepository.existsByEmail(dto.email())){
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        Administrator administrator = new Administrator(
                dto.fistName(),
                dto.lastName(),
                dto.email(),
                dto.password(), //ALTER LATER
                AdminStatus.ACTIVE,
                dto.role()
        );

        administratorRepository.save(administrator);

        return AdministratorResponseDTO.entityFromDTO(administrator);
    }

    public AdministratorResponseDTO getById(Long id) {
        Administrator administrator = administratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found"));

        return AdministratorResponseDTO.entityFromDTO(administrator);
    }

    public Page<AdministratorResponseDTO> getAll(Pageable pageable) {
        return administratorRepository.findAll(pageable)
                .map(AdministratorResponseDTO::entityFromDTO);
    }

    public void desactivateAdministrator(Long id) {
        Administrator administrator = administratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found"));

        administrator.setStatus(AdminStatus.INACTIVE);
        administratorRepository.save(administrator);
    }
}
