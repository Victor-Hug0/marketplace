package br.com.victor.Marketplace.entity.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "is_active",  nullable = false)
    private Boolean isActive = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parentCategory;

    @Column(name = "marketplace_fee_percentage")
    private BigDecimal marketplaceFeePercentage;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Category> subcategories = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public BigDecimal getMarketplaceFeePercentage() {
        return marketplaceFeePercentage;
    }

    public void setMarketplaceFeePercentage(BigDecimal marketplaceFeePercentage) {
        this.marketplaceFeePercentage = marketplaceFeePercentage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
