package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.product.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer> {
}
