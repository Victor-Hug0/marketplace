package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.admin.AdminLoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AproveRequestsRepository extends JpaRepository<AdminLoginAttempt, Integer> {
}
