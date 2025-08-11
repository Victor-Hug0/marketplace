package br.com.victor.Marketplace.entity.product;

import jakarta.persistence.*;

@Entity
@Table(name = "product_brands")
public class ProductBrand {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
