package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.PayamentMethod;
import br.com.victor.Marketplace.entity.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponseDTO(
        UUID id,
        BigDecimal amount,
        Integer installments,
        PayamentMethod payamentMethod,
        PaymentStatus paymentStatus,
        LocalDateTime processedAt
) {
}
