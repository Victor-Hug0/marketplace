package br.com.victor.Marketplace.dto.shipping;

import br.com.victor.Marketplace.dto.address.CreateAddressViaCepRequestDTO;
import jakarta.validation.constraints.NotNull;

public record ShippingAddressRequestDTO(
        Long existentAddressId,
        CreateAddressViaCepRequestDTO newAddress,
        @NotNull(message = "Field saveNewAddressForFutureUse can't be null!")
        Boolean saveNewAddressForFutureUse
) {
}
