package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.CreateStoreOwnerNaturalPersonRequestDTO;
import br.com.victor.Marketplace.dto.StoreOwnerNaturalPersonResponseDTO;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.store.StoreOwnerNaturalPerson;
import br.com.victor.Marketplace.exception.CpfAlreadyExistsException;
import br.com.victor.Marketplace.exception.EmailAlreadyExistsException;
import br.com.victor.Marketplace.exception.InvalidPasswordException;
import br.com.victor.Marketplace.repository.StoreOwnerNaturalPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreOwnerService {

    private static final Logger log = LoggerFactory.getLogger(StoreOwnerService.class);
    private final StoreOwnerNaturalPersonRepository storeOwnerNaturalPersonRepository;
    private final AddressService addressService;

    public StoreOwnerService(StoreOwnerNaturalPersonRepository storeOwnerNaturalPersonRepository,  AddressService addressService) {
        this.storeOwnerNaturalPersonRepository = storeOwnerNaturalPersonRepository;
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

        if  (!conflictsStoreOwnerList.isEmpty()) {
            for (StoreOwnerNaturalPerson conflictsStoreOwner : conflictsStoreOwnerList) {
                if (conflictsStoreOwner.getSsn().equalsIgnoreCase(dto.ssn())) {
                    throw new CpfAlreadyExistsException("Cpf already exists");
                }

                if (conflictsStoreOwner.getEmail().equalsIgnoreCase(dto.email())) {
                    throw new EmailAlreadyExistsException("Email already exists");
                }
            }
        }

        Address address = addressService.createAddress(dto.address());

        StoreOwnerNaturalPerson storeOwnerNaturalPerson = new StoreOwnerNaturalPerson(
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                dto.gender(),
                address,
                dto.firstName(),
                dto.lastName(),
                dto.ssn(),
                dto.birthDate()
        );

        storeOwnerNaturalPersonRepository.save(storeOwnerNaturalPerson);

        return StoreOwnerNaturalPersonResponseDTO.entityFromDTO(storeOwnerNaturalPerson);
    }
}
