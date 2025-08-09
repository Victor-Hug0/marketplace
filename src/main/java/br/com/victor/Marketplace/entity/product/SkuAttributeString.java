package br.com.victor.Marketplace.entity.product;

import br.com.victor.Marketplace.entity.enums.AttributeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sku_attribute_string")
public class SkuAttributeString extends SkuAttributeValue<String>{

    @Column(nullable = false)
    private String value;

    public SkuAttributeString(String name, AttributeType type, String value) {
        super(name, type);
        this.value = value;
    }

    public SkuAttributeString() {}

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
