package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.entity.product.SkuStock;
import br.com.victor.Marketplace.exception.EmptyOrderItemsException;
import br.com.victor.Marketplace.exception.InsufficientStockException;
import br.com.victor.Marketplace.repository.SkuStockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SkuStockService {

    private final SkuStockRepository skuStockRepository;

    public SkuStockService(SkuStockRepository skuStockRepository) {
        this.skuStockRepository = skuStockRepository;
    }

    @Transactional
    public void reserveStock(Long skuId, Integer quantity, Long storeId) {
        SkuStock skuStock = skuStockRepository.findBySkuIdAndStoreId(skuId, storeId)
                .orElseThrow(() -> new EmptyOrderItemsException("Skock not found for SKU: " + skuId + " OR/AND STORE: " + storeId));

        if (skuStock.getAvailableQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock for SKU: " + skuId + "in STORE: " + storeId);
        }

        skuStock.setAvailableQuantity(skuStock.getAvailableQuantity() - quantity);
        skuStock.setReservedQuantity(skuStock.getReservedQuantity() + quantity);
        skuStock.setLastUpdate(LocalDateTime.now());
        skuStockRepository.save(skuStock);
    }

    @Transactional
    public void releaseStock(Long skuId, Integer quantity, Long storeId) {
        SkuStock skuStock = skuStockRepository.findBySkuIdAndStoreId(skuId, storeId)
                .orElseThrow(() -> new EmptyOrderItemsException("Skock not found for SKU: " + skuId + " OR/AND STORE: " + storeId));

        skuStock.setAvailableQuantity(skuStock.getAvailableQuantity() + quantity);
        skuStock.setReservedQuantity(skuStock.getReservedQuantity() - quantity);
        skuStock.setLastUpdate(LocalDateTime.now());
        skuStockRepository.save(skuStock);
    }

    @Transactional
    public void confirmSale(Long skuId, Integer quantity, Long storeId) {
        SkuStock skuStock = skuStockRepository.findBySkuIdAndStoreId(skuId, storeId)
                .orElseThrow(() -> new EmptyOrderItemsException("Skock not found for SKU: " + skuId + " OR/AND STORE: " + storeId));

        skuStock.setReservedQuantity(skuStock.getReservedQuantity() - quantity);
        skuStock.setLastUpdate(LocalDateTime.now());
        skuStockRepository.save(skuStock);
    }

    public Integer getAvailableQuantity(Long skuId, Long storeId) {
        SkuStock skuStock = skuStockRepository.findBySkuIdAndStoreId(skuId, storeId)
                .orElseThrow(() -> new EmptyOrderItemsException("Skock not found for SKU: " + skuId + " OR/AND STORE: " + storeId));

        return skuStock.getAvailableQuantity();
    }
}
