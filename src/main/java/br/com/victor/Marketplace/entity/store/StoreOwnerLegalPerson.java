package br.com.victor.Marketplace.entity.store;

import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.enums.Gender;
import br.com.victor.Marketplace.entity.enums.StoreOwnerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "legal_person_owner")
@PrimaryKeyJoinColumn(name = "id")
public class StoreOwnerLegalPerson extends StoreOwner {

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "fantasy_name")
    private String fantasyName;

    @Column(name = "company_registration_number",  nullable = false,  unique = true)
    private String companyRegistrationNumber;

    public StoreOwnerLegalPerson(String email, String password, String phoneNumber, Gender gender, Address address, String companyName, String fantasyName, String companyRegistrationNumber) {
        super(email, password, phoneNumber, gender, address);
        this.companyName = companyName;
        this.fantasyName = fantasyName;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.setStoreOwnerType(StoreOwnerType.LEGAL_PERSON);
    }

    public StoreOwnerLegalPerson() {}

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
}
