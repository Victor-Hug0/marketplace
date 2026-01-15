package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.store.CreateStoreRequestDTO;
import br.com.victor.Marketplace.dto.store.StoreResponseDTO;
import br.com.victor.Marketplace.entity.store.Store;
import br.com.victor.Marketplace.entity.store.StoreOwner;
import br.com.victor.Marketplace.entity.enums.StoreStatus;
import br.com.victor.Marketplace.exception.*;
import br.com.victor.Marketplace.repository.StoreOwnerNaturalPersonRepository;
import br.com.victor.Marketplace.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreOwnerNaturalPersonRepository storeOwnerNaturalPersonRepository;

    public StoreService(StoreRepository storeRepository,  StoreOwnerNaturalPersonRepository storeOwnerNaturalPersonRepository) {
        this.storeRepository = storeRepository;
        this.storeOwnerNaturalPersonRepository = storeOwnerNaturalPersonRepository;
    }

    @Transactional
    public StoreResponseDTO createStore(CreateStoreRequestDTO dto) {

        List<Store> conflictStores = storeRepository.findByCompanyNameOrFantasyNameOrCompanyRegistrationNumber(
                dto.companyName(),
                dto.fantasyName(),
                dto.companyRegistrationNumber()
        );

        if (!conflictStores.isEmpty()) {
            for  (Store conflictStore : conflictStores) {
                if (conflictStore.getCompanyName().equalsIgnoreCase(dto.companyName())) {
                    throw new CompanyNameAlredyExistsException("Company name already exists");
                }

                if (conflictStore.getFantasyName().equalsIgnoreCase(dto.fantasyName())) {
                    throw new FantasyNameAlreadyExistsException("Fantasy name already exists");
                }

                if (conflictStore.getCompanyRegistrationNumber().equalsIgnoreCase(dto.companyRegistrationNumber())) {
                    throw new CompanyRegistrationNumberAlreadyExistsException("Company registration number already exists");
                }
            }
        }

        StoreOwner storeOwner = storeOwnerNaturalPersonRepository.findById(Math.toIntExact(dto.ownerId()))
                .orElseThrow(() -> new RuntimeException("Store owner not found with ID: " + dto.ownerId()));

        Store store = new Store(
                dto.companyName(),
                dto.fantasyName(),
                dto.companyRegistrationNumber(),
                storeOwner,
                dto.biography(),
                dto.contactEmail(),
                dto.contactPhoneNumber(),
                StoreStatus.UNDER_REVIEW,
                BigDecimal.ZERO,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        storeRepository.save(store);

        return StoreResponseDTO.entityFromDTO(store);
    }

    public StoreResponseDTO getStoreById(Long id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with ID: " + id));

        return StoreResponseDTO.entityFromDTO(store);
    }

    public Page<StoreResponseDTO> getAllStores(Pageable pageable) {
        return storeRepository.findAll(pageable).map(StoreResponseDTO::entityFromDTO);
    }

    public Store getStoreBySkuId(Long skuId) {
        return storeRepository.findBySkuId(skuId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found through of skuId: " + skuId));
    }
}
