package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.CreateStoreOwnerLegalPersonRequestDTO;
import br.com.victor.Marketplace.dto.CreateStoreOwnerNaturalPersonRequestDTO;
import br.com.victor.Marketplace.dto.StoreOwnerLegalPersonResponseDTO;
import br.com.victor.Marketplace.dto.StoreOwnerNaturalPersonResponseDTO;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.store.StoreOwnerLegalPerson;
import br.com.victor.Marketplace.entity.store.StoreOwnerNaturalPerson;
import br.com.victor.Marketplace.exception.CpfAlreadyExistsException;
import br.com.victor.Marketplace.exception.EmailAlreadyExistsException;
import br.com.victor.Marketplace.exception.InvalidPasswordException;
import br.com.victor.Marketplace.exception.ValidationException;
import br.com.victor.Marketplace.repository.StoreOwnerLegalPersonRepository;
import br.com.victor.Marketplace.repository.StoreOwnerNaturalPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreOwnerService {

    private static final Logger log = LoggerFactory.getLogger(StoreOwnerService.class);
    private final StoreOwnerNaturalPersonRepository storeOwnerNaturalPersonRepository;
    private final StoreOwnerLegalPersonRepository storeOwnerLegalPersonRepository;
    private final AddressService addressService;

    public StoreOwnerService(StoreOwnerNaturalPersonRepository storeOwnerNaturalPersonRepository,  AddressService addressService,  StoreOwnerLegalPersonRepository storeOwnerLegalPersonRepository) {
        this.storeOwnerNaturalPersonRepository = storeOwnerNaturalPersonRepository;
        this.storeOwnerLegalPersonRepository = storeOwnerLegalPersonRepository;
        this.addressService = addressService;
    }

    public StoreOwnerNaturalPersonResponseDTO createStoreOwnerNaturalPerson(CreateStoreOwnerNaturalPersonRequestDTO dto) {

        log.info(dto.toString());

        if (!dto.password().equals(dto.passwordConfirmation())) {
            throw new InvalidPasswordException("Passwords don't match");
        }

        List<StoreOwnerNaturalPerson> conflictsStoreOwnerList = storeOwnerNaturalPersonRepository.findBySsnOrEmail(
                dto.ssn(),
                dto.email()
        );

        List<String> errors = new ArrayList<>();

        if  (!conflictsStoreOwnerList.isEmpty()) {
            for (StoreOwnerNaturalPerson conflictsStoreOwner : conflictsStoreOwnerList) {
                if (conflictsStoreOwner.getSsn().equalsIgnoreCase(dto.ssn())) {
                    errors.add("Email already exists: " + conflictsStoreOwner.getEmail());
                }

                if (conflictsStoreOwner.getEmail().equalsIgnoreCase(dto.email())) {
                    errors.add("CPF already exists: " + conflictsStoreOwner.getEmail());
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        Address address = addressService.createAddress(dto.address());

        StoreOwnerNaturalPerson storeOwnerNaturalPerson = new StoreOwnerNaturalPerson(
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                address,
                dto.firstName(),
                dto.lastName(),
                dto.ssn(),
                dto.gender(),
                dto.birthDate()
        );

        storeOwnerNaturalPersonRepository.save(storeOwnerNaturalPerson);

        return StoreOwnerNaturalPersonResponseDTO.entityFromDTO(storeOwnerNaturalPerson);
    }

    public StoreOwnerLegalPersonResponseDTO createStoreOwnerLegalPerson(CreateStoreOwnerLegalPersonRequestDTO dto) {

        if (!dto.password().equals(dto.passwordConfirmation())) {
            throw new InvalidPasswordException("Passwords don't match");
        }

        List<StoreOwnerLegalPerson> conflictStoreOwnerLegalPersonList = storeOwnerLegalPersonRepository.findByCompanyNameOrCompanyRegistrationNumberOrFantasyNameOrEmail(
                dto.companyName(),
                dto.companyRegistrationNumber(),
                dto.fantasyName(),
                dto.email()
        );

        List<String> errors = new ArrayList<>();

        if  (!conflictStoreOwnerLegalPersonList.isEmpty()) {
            for (StoreOwnerLegalPerson conflictStoreOwner : conflictStoreOwnerLegalPersonList) {
                if (conflictStoreOwner.getCompanyName().equalsIgnoreCase(dto.companyName())) {
                    errors.add("Company name already exists: " +  dto.companyName());
                }
                if (conflictStoreOwner.getCompanyRegistrationNumber().equalsIgnoreCase(dto.companyRegistrationNumber())) {
                    errors.add("Registration number already exists: " +  dto.companyRegistrationNumber());
                }
                if (conflictStoreOwner.getFantasyName().equalsIgnoreCase(dto.fantasyName())) {
                    errors.add("Fantasy name already exists: " +  dto.fantasyName());
                }
                if (conflictStoreOwner.getEmail().equalsIgnoreCase(dto.email())) {
                    errors.add("Email already exists: " +  dto.email());
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        Address address = addressService.createAddress(dto.address());

        StoreOwnerLegalPerson storeOwnerLegalPerson = new StoreOwnerLegalPerson(
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                address,
                dto.companyName(),
                dto.fantasyName(),
                dto.companyRegistrationNumber()
        );

        storeOwnerLegalPersonRepository.save(storeOwnerLegalPerson);

        return StoreOwnerLegalPersonResponseDTO.entityFromDTO(storeOwnerLegalPerson);
    }
}
