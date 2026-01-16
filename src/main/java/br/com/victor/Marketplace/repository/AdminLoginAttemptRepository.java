package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.admin.AdminLoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginAttemptRepository extends JpaRepository<AdminLoginAttempt, Integer> {
}
