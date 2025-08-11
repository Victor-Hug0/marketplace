package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.store.StoreOwnerNaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreOwnerNaturalPersonRepository extends JpaRepository<StoreOwnerNaturalPerson, Integer> {
    boolean existsBySsn(String ssn);

    List<StoreOwnerNaturalPerson> findBySsnOrEmail(String ssn, String email);
}

