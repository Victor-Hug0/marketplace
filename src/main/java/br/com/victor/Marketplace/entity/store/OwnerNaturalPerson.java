package br.com.victor.Marketplace.entity.store;

import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.enums.Gender;
import br.com.victor.Marketplace.entity.enums.StoreOwnerType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "natural_person_owner")
@PrimaryKeyJoinColumn(name = "id")
public class OwnerNaturalPerson extends StoreOwner{

    @Column(name = "first_name",  nullable = false)
    private String firstName;

    @Column(name = "last_name",   nullable = false)
    private String lastName;

    @Column(nullable = false,  unique = true)
    private String ssn;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    public OwnerNaturalPerson(String email, String password, String phoneNumber, Gender gender, Address address, String firstName, String lastName, String ssn) {
        super(email, password, phoneNumber, gender, address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.setStoreOwnerType(StoreOwnerType.NATURAL_PERSON);
    }

    public OwnerNaturalPerson() {}
}
