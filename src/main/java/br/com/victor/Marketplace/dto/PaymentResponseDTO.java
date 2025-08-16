package br.com.victor.Marketplace.dto;

import br.com.victor.Marketplace.entity.enums.PayamentMethod;
import br.com.victor.Marketplace.entity.enums.PaymentStatus;
import br.com.victor.Marketplace.entity.order.OrderPayment;

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

    public static PaymentResponseDTO entityFromDTO(OrderPayment orderPayment) {
        return new PaymentResponseDTO(
                orderPayment.getId(),
                orderPayment.getAmount(),
                orderPayment.getInstallments(),
                orderPayment.getPayamentMethod(),
                orderPayment.getPaymentStatus(),
                orderPayment.getProcessedAt()
        );
    }
}
