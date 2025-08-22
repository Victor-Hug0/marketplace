package br.com.victor.Marketplace.entity.admin;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "administrators")
public class Administrator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true,  nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminRole role;

    @OneToMany(mappedBy = "revisor")
    private List<AproveRequests> aproveRequests;

    @OneToMany(mappedBy = "administrator")
    private List<AdminLoginAttempt> loginAttempts;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",  nullable = false)
    private LocalDateTime updatedAt;


    public Administrator(String firstName, String lastName, String email, String password, AdminStatus status, AdminRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public Administrator() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public AdminStatus getStatus() {
        return status;
    }

    public void setStatus(AdminStatus status) {
        this.status = status;
    }

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }

    public List<AproveRequests> getAproveRequests() {
        return aproveRequests;
    }

    public void setAproveRequests(List<AproveRequests> aproveRequests) {
        this.aproveRequests = aproveRequests;
    }

    public List<AdminLoginAttempt> getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(List<AdminLoginAttempt> loginAttempts) {
        this.loginAttempts = loginAttempts;
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
