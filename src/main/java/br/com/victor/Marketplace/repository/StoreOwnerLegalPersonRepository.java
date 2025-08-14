package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.store.StoreOwnerLegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreOwnerLegalPersonRepository extends JpaRepository<StoreOwnerLegalPerson, Integer> {

    List<StoreOwnerLegalPerson> findByCompanyNameOrCompanyRegistrationNumberOrFantasyNameOrEmail(String companyName, String companyRegistrationNumber, String fantasyName, String email);
}
