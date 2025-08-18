package br.com.victor.Marketplace.entity.product;

import br.com.victor.Marketplace.entity.enums.SkuColor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    @OneToOne(mappedBy = "sku", cascade = CascadeType.ALL, orphanRemoval = true)
    private SkuStock skuStock;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SkuColor color;

    @Column(nullable = false)
    private Integer width;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer length;

    @Column(nullable = false)
    private BigDecimal weight;

    @OneToMany(mappedBy = "sku",   cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuAttributeDecimal> skuAttributesDecimal = new ArrayList<>();

    @OneToMany(mappedBy = "sku",   cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuAttributeInteger> skuAttributesInteger = new ArrayList<>();

    @OneToMany(mappedBy = "sku",   cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuAttributeString>  skuAttributesString = new ArrayList<>();

    public Sku() {}

    public Sku(String skuCode, BigDecimal price, Product product, SkuColor color, Integer width, Integer height, Integer length, BigDecimal weight) {
        this.skuCode = skuCode;
        this.price = price;
        this.product = product;
        this.color = color;
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
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

    public SkuStock getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(SkuStock skuStock) {
        this.skuStock = skuStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public SkuColor getColor() {
        return color;
    }

    public void setColor(SkuColor color) {
        this.color = color;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void addDecimalAttribute(SkuAttributeDecimal attribute) {
        this.skuAttributesDecimal.add(attribute);
        attribute.setSku(this);
    }

    public void addIntegerAttribute(SkuAttributeInteger attribute) {
        this.skuAttributesInteger.add(attribute);
        attribute.setSku(this);
    }

    public void addStringAttribute(SkuAttributeString attribute) {
        this.skuAttributesString.add(attribute);
        attribute.setSku(this);
    }
}
