package br.com.victor.Marketplace.entity.store;

import br.com.victor.Marketplace.entity.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "fantasy_name",  nullable = false,  unique = true)
    private String fantasyName;

    @Column(name = "company_registration_number",   nullable = false,  unique = true)
    private String companyRegistrationNumber;

    @Column(nullable = false)
    private String biography;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "banner_url")
    private String bannerUrl;

    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;

    @Column(name = "contact_email",   nullable = false,  unique = true)
    private String contactEmail;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    @Column(name = "store_status",  nullable = false)
    private StoreStatus storeStatus;

    @Column(name = "average_rating", precision = 2, scale = 1, nullable = false)
    private BigDecimal averageRating;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "activate_at")
    private LocalDateTime activateAt;

    @Column(name = "updated_at",  nullable = false)
    private LocalDateTime updatedAt;

    public Store(String companyName, String fantasyName, String companyRegistrationNumber, String biography, String contactEmail, StoreStatus storeStatus, LocalDateTime createdAt, LocalDateTime updatedAt, BigDecimal averageRating) {
        this.companyName = companyName;
        this.fantasyName = fantasyName;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.biography = biography;
        this.contactEmail = contactEmail;
        this.storeStatus = storeStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.averageRating = averageRating;
    }

    public Store() {
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public StoreStatus getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(StoreStatus storeStatus) {
        this.storeStatus = storeStatus;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getActivateAt() {
        return activateAt;
    }

    public void setActivateAt(LocalDateTime activateAt) {
        this.activateAt = activateAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
