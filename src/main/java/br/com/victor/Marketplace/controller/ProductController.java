package br.com.victor.Marketplace.controller;

import br.com.victor.Marketplace.dto.CreateProductRequestDTO;
import br.com.victor.Marketplace.dto.ProductResponseDTO;
import br.com.victor.Marketplace.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
