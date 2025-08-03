package br.com.victor.Marketplace.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String street;
    private String complement;
    @Column(nullable = false)
    private String number;

    public Address(String zipCode, String state, String city, String neighborhood, String street, String number) {
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
    }

    public Address() {}

    public Long getId() {
        return id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
