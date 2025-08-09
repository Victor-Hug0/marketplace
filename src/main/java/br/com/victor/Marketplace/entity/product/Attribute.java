package br.com.victor.Marketplace.entity.product;

import jakarta.persistence.*;

@Entity
@Table(name = "attributes")
public class Attribute {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttributeType type;

    public Attribute(String name, AttributeType type) {
        this.name = name;
        this.type = type;
    }

    public Attribute() {}

    public Long getId() {
        return id;
    }

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
}
