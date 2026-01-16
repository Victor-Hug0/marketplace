package br.com.victor.Marketplace.entity.carrier;

import br.com.victor.Marketplace.entity.enums.FuelType;
import br.com.victor.Marketplace.entity.enums.VehicleStatus;
import br.com.victor.Marketplace.entity.enums.VehicleType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String manufacturer;

    @Column(name = "plate_number", nullable = false, unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private String renavam;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String chassis;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    private Carrier carrier;

    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "load_capacity", nullable = false)
    private Integer loadCapacity;

    @Column(name = "volumetric_capacity", nullable = false)
    private Integer volumetricCapacity;

    @Column(name = "total_gross_weight", nullable = false)
    private Integer totalGrossWeight;

    @Column(nullable = false)
    private Integer length;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer width;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "vehicle_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    @Column(nullable = false, unique = true)
    private String crlv;

    @Column(name = "license_expiration_date", nullable = false)
    private LocalDate licenseExpirationDate;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleImage> images;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Vehicle(String model, String manufacturer, String plateNumber, String renavam, String color, String chassis, Integer year, Carrier carrier, VehicleType vehicleType, Integer loadCapacity, Integer volumetricCapacity, Integer totalGrossWeight, Integer length, Integer height, Integer width, FuelType fuelType, VehicleStatus vehicleStatus, String crlv, LocalDate licenseExpirationDate, List<VehicleImage> images) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.plateNumber = plateNumber;
        this.renavam = renavam;
        this.color = color;
        this.chassis = chassis;
        this.year = year;
        this.carrier = carrier;
        this.vehicleType = vehicleType;
        this.loadCapacity = loadCapacity;
        this.volumetricCapacity = volumetricCapacity;
        this.totalGrossWeight = totalGrossWeight;
        this.length = length;
        this.height = height;
        this.width = width;
        this.fuelType = fuelType;
        this.vehicleStatus = vehicleStatus;
        this.crlv = crlv;
        this.licenseExpirationDate = licenseExpirationDate;
        this.images = images;
    }

    public Vehicle() {}
}
