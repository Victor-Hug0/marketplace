package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.CreateAttributeRequestDTO;
import br.com.victor.Marketplace.dto.CreateProductRequestDTO;
import br.com.victor.Marketplace.dto.CreateSkuRequestDTO;
import br.com.victor.Marketplace.dto.ProductResponseDTO;
import br.com.victor.Marketplace.entity.enums.AttributeType;
import br.com.victor.Marketplace.entity.product.*;
import br.com.victor.Marketplace.entity.store.Store;
import br.com.victor.Marketplace.exception.ResourceNotFoundException;
import br.com.victor.Marketplace.repository.CategoryRepository;
import br.com.victor.Marketplace.repository.ProductBrandRepository;
import br.com.victor.Marketplace.repository.ProductRepository;
import br.com.victor.Marketplace.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ProductBrandRepository productBrandRepository;
    private final CategoryRepository  categoryRepository;

    public ProductService(ProductRepository productRepository,
                          StoreRepository storeRepository,
                          ProductBrandRepository productBrandRepository,
                          CategoryRepository  categoryRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.productBrandRepository = productBrandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ProductResponseDTO createProduct(CreateProductRequestDTO dto) {

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + dto.storeId()));

        ProductBrand productBrand = productBrandRepository.findById(Math.toIntExact(dto.brandId()))
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + dto.brandId()));

        List<Category> categories = categoryRepository.findAllById(dto.categoriesId());

        if (categories.size() != dto.categoriesId().size()) {
            throw new ResourceNotFoundException("One or more categories not found for the given IDs.");
        }

        Product product = new Product(dto.name(), dto.description(), store, productBrand, categories, LocalDateTime.now(), LocalDateTime.now());

        for (CreateSkuRequestDTO skuRequestDTO : dto.skus()) {
            Sku sku = new Sku(skuRequestDTO.skuCode(), skuRequestDTO.price(), skuRequestDTO.stock(), product);
            product.addSku(sku);

            for (CreateAttributeRequestDTO attributeRequestDTO : skuRequestDTO.attributes()) {
                switch (attributeRequestDTO.type()) {
                    case DECIMAL -> {
                        SkuAttributeDecimal skuAttributeDecimal = new SkuAttributeDecimal(attributeRequestDTO.name(), AttributeType.DECIMAL, new BigDecimal(attributeRequestDTO.value()));
                        sku.addDecimalAttribute(skuAttributeDecimal);
                    }
                    case INTEGER -> {
                        SkuAttributeInteger skuAttributeInteger = new SkuAttributeInteger(attributeRequestDTO.name(), AttributeType.INTEGER, Integer.parseInt(attributeRequestDTO.value()));
                        sku.addIntegerAttribute(skuAttributeInteger);
                    }
                    case VARCHAR -> {
                        SkuAttributeString skuAttributeString = new SkuAttributeString(attributeRequestDTO.name(), AttributeType.VARCHAR, attributeRequestDTO.value());
                        sku.addStringAttribute(skuAttributeString);
                    }
                }
            }
        }

        Product savedProduct = productRepository.save(product);

        return ProductResponseDTO.entityFromDTO(savedProduct);
    }
}
