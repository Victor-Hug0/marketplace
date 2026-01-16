package br.com.victor.Marketplace.entity.vehicle;

import br.com.victor.Marketplace.entity.carrier.Driver;
import br.com.victor.Marketplace.entity.enums.VehicleTypeEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle_types")
public class VehicleType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleTypeEnum vehicleType;

    @ManyToMany(mappedBy = "authorizedVehicleTypes")
    private List<Driver> drivers = new ArrayList<>();
}
