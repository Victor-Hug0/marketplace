package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.AdministratorResponseDTO;
import br.com.victor.Marketplace.dto.CreateAdministratorRequestDTO;
import br.com.victor.Marketplace.entity.admin.AdminStatus;
import br.com.victor.Marketplace.entity.admin.Administrator;
import br.com.victor.Marketplace.exception.EmailAlreadyExistsException;
import br.com.victor.Marketplace.exception.InvalidPasswordException;
import br.com.victor.Marketplace.repository.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public AdministratorResponseDTO createAdministrator(CreateAdministratorRequestDTO dto) {

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
}
