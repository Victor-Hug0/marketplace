package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    boolean existsByCompanyName(String companyName);

    boolean existsByFantasyName(String fantasyName);

    boolean existsByCompanyRegistrationNumber(String companyRegistrationNumber);

    List<Store> findByCompanyNameOrFantasyNameOrCompanyRegistrationNumber(
            String companyName,
            String fantasyName,
            String companyRegistrationNumber
    );

    @Query("SELECT st FROM Sku s JOIN s.product p JOIN p.store st WHERE s.id = :skuId")
    Optional<Store> findBySkuId(@Param("skuId") Long skuId);
}
