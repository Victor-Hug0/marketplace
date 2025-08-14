package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.store.Store;
import br.com.victor.Marketplace.entity.enums.StoreStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record StoreResponseDTO(
        Long id,
        String companyName,
        String fantasyName,
        String companyRegistrationNumber,
        String biography,
        String logoUrl,
        String bannerUrl,
        String contactPhoneNumber,
        String contactEmail,
        StoreStatus storeStatus,
        BigDecimal averageRating,
        List<ProductResponseDTO> products,
        LocalDateTime createdAt,
        LocalDateTime activateAt,
        LocalDateTime updatedAt
) {

    public static StoreResponseDTO entityFromDTO(Store store) {

        List<ProductResponseDTO> products = store.getProducts().stream()
                .map(ProductResponseDTO::entityFromDTO)
                .toList();

        return new StoreResponseDTO(
                store.getId(),
                store.getCompanyName(),
                store.getFantasyName(),
                store.getCompanyRegistrationNumber(),
                store.getBiography(),
                store.getLogoUrl(),
                store.getBannerUrl(),
                store.getContactPhoneNumber(),
                store.getContactEmail(),
                store.getStoreStatus(),
                store.getAverageRating(),
                products,
                store.getCreatedAt(),
                store.getActivateAt(),
                store.getUpdatedAt()
        );
    }
}
