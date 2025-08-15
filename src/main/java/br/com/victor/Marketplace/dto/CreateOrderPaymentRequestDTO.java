package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.PayamentMethod;

public record CreateOrderPaymentRequestDTO(
        PayamentMethod payamentMethod,
        Integer installments
) {
}
