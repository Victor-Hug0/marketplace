package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.admin.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
    boolean existsByEmail(String email);
}
