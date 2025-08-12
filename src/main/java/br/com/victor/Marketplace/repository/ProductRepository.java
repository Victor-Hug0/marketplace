package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
