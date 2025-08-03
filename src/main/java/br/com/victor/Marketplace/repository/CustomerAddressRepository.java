package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.address.CustomerAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddresses, Long> {
}
