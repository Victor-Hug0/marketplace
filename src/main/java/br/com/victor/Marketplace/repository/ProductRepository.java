package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> getProductsByStoreId(Pageable pageable, Long storeId);
}
