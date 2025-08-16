package br.com.victor.Marketplace.entity.order;

import br.com.victor.Marketplace.entity.enums.PayamentMethod;
import br.com.victor.Marketplace.entity.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_payments")
public class OrderPayment {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer installments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayamentMethod payamentMethod;

    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "processed_at", nullable = false)
    private LocalDateTime processedAt;

    public OrderPayment(BigDecimal amount, Integer installments, Order order, PayamentMethod payamentMethod, PaymentStatus paymentStatus) {
        this.amount = amount;
        this.installments = installments;
        this.order = order;
        this.payamentMethod = payamentMethod;
        this.paymentStatus = paymentStatus;
        this.processedAt = LocalDateTime.now();
    }

    public OrderPayment() {}

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PayamentMethod getPayamentMethod() {
        return payamentMethod;
    }

    public void setPayamentMethod(PayamentMethod payamentMethod) {
        this.payamentMethod = payamentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
}
