package br.com.victor.Marketplace.entity.carrier;

import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.enums.CnhCategory;
import br.com.victor.Marketplace.entity.enums.Gender;
import br.com.victor.Marketplace.entity.enums.VehicleTypeEnum;
import br.com.victor.Marketplace.entity.vehicle.VehicleType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String nationality;

    @Column(name = "cnh_registry_number", nullable = false, unique = true)
    private String cnhRegistryNumber;

    @Column(name = "cnh_emission_date", nullable = false)
    private LocalDate cnhEmissionDate;

    @Column(name = "cnh_expiration_date", nullable = false)
    private LocalDate cnhExpirationDate;

    @Column(name = "cnh_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private CnhCategory cnhCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    private Carrier carrier;

    @ManyToMany
    @JoinTable(
            name = "driver_authorized_vehicle_types",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_type_id")
    )
    private List<VehicleType> authorizedVehicleTypes;
}
