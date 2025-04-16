
package com.ecommerce.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	 private final OrderService orderService;
	 @Autowired
	    public OrderController(OrderService orderService) {
	        this.orderService = orderService;
	    }

    //  Place a new order
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        String orderNumber = orderService.placeOrder(order);
        //return ResponseEntity.ok("Order placed successfully with Order Number: " + orderNumber);
        return new ResponseEntity<>("Order placed successfully with Order Number: " + orderNumber, HttpStatus.CREATED);
    }

    //  Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    //  Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}