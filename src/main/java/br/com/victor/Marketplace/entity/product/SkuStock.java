package br.com.victor.Marketplace.entity.product;

import br.com.victor.Marketplace.entity.store.Store;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sku_stock")
public class SkuStock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sku_id", nullable = false)
    private Sku sku;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "avaliable_stock", nullable = false)
    private Integer availableQuantity;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public SkuStock(Integer availableQuantity, Sku sku, Store store) {
        this.availableQuantity = availableQuantity;
        this.sku = sku;
        this.store = store;
        this.reservedQuantity = 0;
        this.lastUpdate = LocalDateTime.now();
    }

    public SkuStock() {}

    public Long getId() {
        return id;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
