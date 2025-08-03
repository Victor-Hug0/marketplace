package br.com.victor.Marketplace.entity.customer;

import br.com.victor.Marketplace.entity.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "customer_addresses")
public class CustomerAddresses {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public CustomerAddresses(Address address, Customer customer) {
        this.address = address;
        this.customer = customer;
    }

    public CustomerAddresses() {
    }

    public Address getAddress() {
        return address;
    }

    public Customer getCustomer() {
        return customer;
    }
}
