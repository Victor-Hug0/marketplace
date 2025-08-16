package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT ca.address FROM CustomerAddresses ca WHERE ca.customer.id = :customerId")
    List<Address> findByCustomerId(@Param("customerId") UUID customerId);
}
