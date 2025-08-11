package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {
    boolean existsByCompanyName(String companyName);

    boolean existsByFantasyName(String fantasyName);

    boolean existsByCompanyRegistrationNumber(String companyRegistrationNumber);

    List<Store> findByCompanyNameOrFantasyNameOrCompanyRegistrationNumber(
            String companyName,
            String fantasyName,
            String companyRegistrationNumber
    );
}
