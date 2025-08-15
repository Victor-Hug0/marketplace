package br.com.victor.Marketplace.entity.order;

import br.com.victor.Marketplace.entity.product.Sku;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sku_id", nullable = false)
    @JsonBackReference
    private Sku sku;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "marketplace_fee", nullable = false)
    private BigDecimal marketplaceFee;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;
}
