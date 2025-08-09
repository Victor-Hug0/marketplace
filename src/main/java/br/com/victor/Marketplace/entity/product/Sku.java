package br.com.victor.Marketplace.entity.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "skus")
public class Sku {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku_code",  nullable = false, unique = true)
    private String skuCode;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    @OneToMany(mappedBy = "sku",   cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuAttributeDecimal> skuAttributesDecimal;

    @OneToMany(mappedBy = "sku",   cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuAttributeInteger> skuAttributesInteger;

    @OneToMany(mappedBy = "sku",   cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuAttributeString>  skuAttributesString;

    public Sku() {}

    public Sku(String skuCode, BigDecimal price, Integer stock, Product product, List<SkuAttributeDecimal> skuAttributesDecimal, List<SkuAttributeInteger> skuAttributesInteger, List<SkuAttributeString> skuAttributesString) {
        this.skuCode = skuCode;
        this.price = price;
        this.stock = stock;
        this.product = product;
        this.skuAttributesDecimal = skuAttributesDecimal;
        this.skuAttributesInteger = skuAttributesInteger;
        this.skuAttributesString = skuAttributesString;
    }

    public void addSkuAttributeDecimal(SkuAttributeDecimal skuAttributeDecimal) {
        this.skuAttributesDecimal.add(skuAttributeDecimal);
    }

    public void addSkuAttributeInteger(SkuAttributeInteger skuAttributeInteger) {
        this.skuAttributesInteger.add(skuAttributeInteger);
    }

    public void addSkuAttributeString(SkuAttributeString skuAttributeString) {
        this.skuAttributesString.add(skuAttributeString);
    }

    public Long getId() {
        return id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<SkuAttributeDecimal> getSkuAttributesDecimal() {
        return skuAttributesDecimal;
    }

    public void setSkuAttributesDecimal(List<SkuAttributeDecimal> skuAttributesDecimal) {
        this.skuAttributesDecimal = skuAttributesDecimal;
    }

    public List<SkuAttributeInteger> getSkuAttributesInteger() {
        return skuAttributesInteger;
    }

    public void setSkuAttributesInteger(List<SkuAttributeInteger> skuAttributesInteger) {
        this.skuAttributesInteger = skuAttributesInteger;
    }

    public List<SkuAttributeString> getSkuAttributesString() {
        return skuAttributesString;
    }

    public void setSkuAttributesString(List<SkuAttributeString> skuAttributesString) {
        this.skuAttributesString = skuAttributesString;
    }
}
