package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.entity.product.Sku;
import br.com.victor.Marketplace.exception.ResourceNotFoundException;
import br.com.victor.Marketplace.repository.SkuRepository;
import org.springframework.stereotype.Service;

@Service
public class SkuService {

    private final SkuRepository skuRepository;

    public SkuService(SkuRepository skuRepository) {
        this.skuRepository = skuRepository;
    }

    public Sku getSkuById(Long skuId) {
        return skuRepository.findById(Math.toIntExact(skuId)).orElseThrow(() -> new ResourceNotFoundException("Sku not found with id: " + skuId));
    }
}
