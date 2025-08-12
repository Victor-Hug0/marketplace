package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.product.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<Sku, Integer> {
}
