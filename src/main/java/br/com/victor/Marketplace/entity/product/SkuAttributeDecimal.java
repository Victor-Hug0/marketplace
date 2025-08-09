package br.com.victor.Marketplace.entity.product;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sku_attribute_decimal")
public class SkuAttributeDecimal extends SkuAttributeValue<BigDecimal> {

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal value;

    public SkuAttributeDecimal(String name, AttributeType type, BigDecimal value) {
        super(name, type);
        this.value = value;
    }

    public SkuAttributeDecimal() {}

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
