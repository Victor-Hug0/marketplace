package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.dto.CreateOrderItemRequestDTO;
import br.com.victor.Marketplace.dto.CreateOrderRequestDTO;
import br.com.victor.Marketplace.dto.OrderResponseDTO;
import br.com.victor.Marketplace.entity.address.Address;
import br.com.victor.Marketplace.entity.customer.Customer;
import br.com.victor.Marketplace.entity.enums.PayamentMethod;
import br.com.victor.Marketplace.entity.enums.PaymentStatus;
import br.com.victor.Marketplace.entity.enums.ShippingMethod;
import br.com.victor.Marketplace.entity.enums.ShippingStatus;
import br.com.victor.Marketplace.entity.order.Order;
import br.com.victor.Marketplace.entity.order.OrderItem;
import br.com.victor.Marketplace.entity.order.OrderPayment;
import br.com.victor.Marketplace.entity.order.Shipping;
import br.com.victor.Marketplace.entity.product.Category;
import br.com.victor.Marketplace.entity.product.Sku;
import br.com.victor.Marketplace.exception.EmptyOrderItemsException;
import br.com.victor.Marketplace.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final SkuService skuService;
    private final SkuStockService skuStockService;
    private final CategoryService categoryService;
    private final OrderPaymentService orderPaymentService;

    @Value("${marketplace.fee}")
    private BigDecimal DEFAULT_MARKETPLACE_FEE;

    public OrderService(OrderRepository orderRepository,
                        CustomerService customerService,
                        AddressService addressService,
                        SkuService skuService,
                        SkuStockService skuStockService,
                        CategoryService categoryService,
                        OrderPaymentService orderPaymentService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.addressService = addressService;
        this.skuService = skuService;
        this.skuStockService = skuStockService;
        this.categoryService = categoryService;
        this.orderPaymentService = orderPaymentService;
    }

    @Transactional
    public OrderResponseDTO createOrder(CreateOrderRequestDTO dto) {

        Customer customer = customerService.getCustomerById(dto.customerId());
        Address deliveryAddress = addressService.getOrCreateAddress(dto.shippingAddress(), customer);

        if (dto.orderItems().isEmpty()) {
            throw new EmptyOrderItemsException("Order items cannot be empty");
        }

        BigDecimal totalItemsAmount = BigDecimal.ZERO;
        BigDecimal totalTaxAmount = BigDecimal.ZERO;
        BigDecimal totalMarketPlaceFee = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CreateOrderItemRequestDTO item : dto.orderItems()) {
            Sku sku = skuService.getSkuById(item.skuId());
            Integer quantity = item.quantity();
            Long storeId = sku.getProduct().getStore().getId();
            skuStockService.reserveStock(sku.getId(), quantity, storeId);

            BigDecimal unitPrice = sku.getPrice();
            BigDecimal itemSubTotal = unitPrice.multiply(new BigDecimal(quantity));

            OrderItem orderItem = new OrderItem(sku, quantity, unitPrice, itemSubTotal);
            totalItemsAmount = totalItemsAmount.add(orderItem.getSubTotal());

            Category productCategory = sku.getProduct().getCategories().getFirst();
            BigDecimal itemMarketPlaceFeePercentage = categoryService.getMarketPlaceFeeForCategory(productCategory, DEFAULT_MARKETPLACE_FEE);
            BigDecimal itemMarketPlaceFee = itemSubTotal.multiply(itemMarketPlaceFeePercentage.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
            totalMarketPlaceFee = totalMarketPlaceFee.add(itemMarketPlaceFee);

            orderItems.add(orderItem);
        }

        BigDecimal shippingCost = BigDecimal.ZERO; //TODO
        Shipping shipping = new Shipping(deliveryAddress, BigDecimal.ZERO, ShippingMethod.LOCAL_PICKUP, LocalDateTime.now().plusDays(10));
        shipping.setStatus(ShippingStatus.WAITING_PAYMENT);

        BigDecimal amount = totalItemsAmount.add(shippingCost).add(totalTaxAmount).add(totalMarketPlaceFee);
        Integer installments = dto.payment().installments();
        PayamentMethod payamentMethod = dto.payment().payamentMethod();

        Order order = new Order(customer, shipping, totalItemsAmount, totalTaxAmount, orderItems, totalMarketPlaceFee);

        Order savedOrder = orderRepository.save(order);

        orderItems.forEach(orderItem -> {
            orderItem.setOrder(savedOrder);
        });

        OrderPayment orderPayment = new OrderPayment(amount, installments, savedOrder, payamentMethod, PaymentStatus.PENDING);

        savedOrder.getPayments().add(orderPayment);

        orderPaymentService.save(orderPayment);

        return OrderResponseDTO.entityFromDTO(order);
    }
}
