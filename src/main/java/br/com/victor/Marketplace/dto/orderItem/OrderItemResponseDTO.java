package br.com.victor.Marketplace.dto.orderItem;

import br.com.victor.Marketplace.dto.sku.SkuResponseDTO;
import br.com.victor.Marketplace.entity.order.OrderItem;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        SkuResponseDTO sku,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subTotal
) {

    public static OrderItemResponseDTO entityFromDTO(OrderItem orderItem) {

        SkuResponseDTO skuResponseDTO = SkuResponseDTO.entityFromDTO(orderItem.getSku());

        return new OrderItemResponseDTO(
                orderItem.getId(),
                skuResponseDTO,
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getSubTotal()
        );
    }
}
