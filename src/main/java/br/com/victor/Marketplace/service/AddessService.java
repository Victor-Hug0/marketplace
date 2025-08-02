package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddessService {

    private final AddressRepository addressRepository;

    public AddessService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    
}
