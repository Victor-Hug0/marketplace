package br.com.victor.Marketplace.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sku_attribute_integer")
public class SkuAttributeInteger extends SkuAttributeValue<Integer> {

    @Column(nullable = false)
    private Integer value;

    public SkuAttributeInteger(String name, AttributeType type, Integer value) {
        super(name, type);
        this.value = value;
    }

    public SkuAttributeInteger() {}

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}
