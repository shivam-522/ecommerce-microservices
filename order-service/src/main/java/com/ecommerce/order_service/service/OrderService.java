

package com.ecommerce.order_service.service;


import com.ecommerce.order_service.client.InventoryClient;
import com.ecommerce.order_service.client.PaymentClient;
import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.model.client.InventoryResponse;
import com.ecommerce.order_service.model.client.PaymentRequest;
import com.ecommerce.order_service.model.client.PaymentResponse;
import com.ecommerce.order_service.model.client.ProductResponse;
import com.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	 private final ProductClient productClient;
	    private final InventoryClient inventoryClient;
	    private final PaymentClient paymentClient;
	    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository,ProductClient productClient,
            InventoryClient inventoryClient,
            PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.productClient=productClient;
        this.inventoryClient=inventoryClient;
        this.paymentClient=paymentClient;
    }


    // Place an order
    public String placeOrder(Order order) {
        // 1. Get Product Details
        ProductResponse product = productClient.getProductById(order.getProductId());

        // 2. Check Inventory
        System.out.println("Product "+order.getProductId());
        InventoryResponse inventory = inventoryClient.checkStock(order.getProductId());
        if (inventory.getQuantity() < order.getQuantity()) {
            throw new RuntimeException("Product not in stock!");
        }
        
        // Create and Save Order with status "PENDING"
        //Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setProductId(order.getProductId());
        order.setQuantity(order.getQuantity());
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        // 4. Reduce Inventory
        inventoryClient.reduceStock(order.getProductId(), order.getQuantity());

        // 5. Make Payment
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(savedOrder.getId());
        paymentRequest.setAmount(product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        paymentRequest.setPaymentMethod("CARD");

        PaymentResponse payment = paymentClient.makePayment(paymentRequest);

        if (!payment.getPaymentStatus().equalsIgnoreCase("SUCCESS")) {
            throw new RuntimeException("Payment failed!");
        }

        // Update Order Status to CONFIRMED
        savedOrder.setStatus("CONFIRMED");
        orderRepository.save(savedOrder);

        return savedOrder.getOrderNumber();
    }


    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }
}
