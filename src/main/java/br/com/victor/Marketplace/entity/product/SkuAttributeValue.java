package br.com.victor.Marketplace.entity.product;

import br.com.victor.Marketplace.entity.enums.AttributeType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class SkuAttributeValue<T> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttributeType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id",  nullable = false)
    @JsonBackReference
    private Sku sku;

    public SkuAttributeValue(String name, AttributeType type) {
        this.name = name;
        this.type = type;
    }

    public SkuAttributeValue() {}

    public abstract T getValue();
    public abstract void setValue(T value);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }
}
