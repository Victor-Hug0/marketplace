package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.product.SkuStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SkuStockRepository extends JpaRepository<SkuStock, Long> {
    Optional<SkuStock> findBySkuIdAndStoreId(Long skuId, Long storeId);
}
