package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
