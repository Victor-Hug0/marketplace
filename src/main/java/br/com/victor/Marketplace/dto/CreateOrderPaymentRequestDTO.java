package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.PayamentMethod;
import jakarta.validation.constraints.NotNull;

public record CreateOrderPaymentRequestDTO(
        @NotNull(message = "Field payamentMethod can't be null!")
        PayamentMethod payamentMethod,
        @NotNull(message = "Field installments can't be null!")
        Integer installments
) {
}
