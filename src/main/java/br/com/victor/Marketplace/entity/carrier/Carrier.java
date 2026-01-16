package br.com.victor.Marketplace.entity.carrier;

import br.com.victor.Marketplace.entity.enums.CarrierStatus;
import br.com.victor.Marketplace.entity.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carriers")
public class Carrier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "corporate_reason", nullable = false, unique = true)
    private String corporateReason;

    @Column(name = "fantasy_name", nullable = false, unique = true)
    private String fantasyName;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(name = "state_registration", nullable = false, unique = true)
    private String stateRegistration;

    @Column(name = "rntrc", nullable = false, unique = true)
    private String rntrc;

    @Column(nullable = false)
    private String cnae;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    private String bank;
    private String agency;
    private String account;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarrierStatus carrierStatus;

    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Vehicle> vehicles;
}
