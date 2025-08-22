package br.com.victor.Marketplace.entity.admin;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_login_attempts")
public class AdminLoginAttempt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "administrator_id",  nullable = false)
    private Administrator administrator;

    @Column(name = "ip_address",  nullable = false)
    private String ipAddress;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "was_successful",  nullable = false)
    private Boolean successful;

    public AdminLoginAttempt(Administrator administrator, String ipAddress, LocalDateTime dateTime,  Boolean successful) {
        this.administrator = administrator;
        this.ipAddress = ipAddress;
        this.dateTime = dateTime;
        this.successful = successful;
    }

    public AdminLoginAttempt() {}

    public Long getId() {
        return id;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
