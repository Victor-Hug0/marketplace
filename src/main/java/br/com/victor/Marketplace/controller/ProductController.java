package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.CreateProductRequestDTO;
import br.com.victor.Marketplace.dto.ProductResponseDTO;
import br.com.victor.Marketplace.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {

        ProductResponseDTO responseDTO =  productService.createProduct(createProductRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(Pageable pageable) {
        Page<ProductResponseDTO> responseDTO = productService.getAllProducts(pageable);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<Page<ProductResponseDTO>> getProductsByStoreId(@PathVariable Long storeId, Pageable pageable) {
        Page<ProductResponseDTO> productResponseDTO = productService.getAllStoreProductsByStoreId(pageable, storeId);

        return ResponseEntity.ok(productResponseDTO);
    }
}
