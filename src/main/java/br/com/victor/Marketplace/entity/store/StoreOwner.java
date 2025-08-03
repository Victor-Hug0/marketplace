package br.com.victor.Marketplace.entity.store;

import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.enums.Gender;
import br.com.victor.Marketplace.entity.enums.StoreOwnerType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "store_owner")
@Inheritance(strategy = InheritanceType.JOINED)
public class StoreOwner {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String  email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "store_owner_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreOwnerType storeOwnerType;

    @JoinColumn(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",  nullable = false)
    private LocalDateTime updatedAt;

    public StoreOwner(String email, String password, String phoneNumber, Gender gender, Address address) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public StoreOwner() {}

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public StoreOwnerType getStoreOwnerType() {
        return storeOwnerType;
    }

    public void setStoreOwnerType(StoreOwnerType storeOwnerType) {
        this.storeOwnerType = storeOwnerType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
