package br.com.victor.Marketplace.entity.store;

import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.enums.Gender;
import br.com.victor.Marketplace.entity.enums.StoreOwnerType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "natural_person_owner")
@PrimaryKeyJoinColumn(name = "id")
public class StoreOwnerNaturalPerson extends StoreOwner{

    @Column(name = "first_name",  nullable = false)
    private String firstName;

    @Column(name = "last_name",   nullable = false)
    private String lastName;

    @Column(nullable = false,  unique = true)
    private String ssn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    public StoreOwnerNaturalPerson(String email, String password, String phoneNumber, Address address, String firstName, String lastName, String ssn, Gender gender, LocalDate birthDate) {
        super(email, password, phoneNumber, address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.gender = gender;
        this.birthDate = birthDate;
        this.setStoreOwnerType(StoreOwnerType.NATURAL_PERSON);
    }

    public StoreOwnerNaturalPerson() {}

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

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
