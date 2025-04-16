package com.ecommerce.order_service.model;



import jakarta.persistence.*;
//import lombok.*;

@Entity
@Table(name = "orders") // "order" is a reserved keyword in SQL, so use "orders"

public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private Long productId;
    private Integer quantity;
    private String status; // Example: PENDING, CONFIRMED, SHIPPED
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order(Long id, String orderNumber, Long productId, Integer quantity, String status) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.productId = productId;
		this.quantity = quantity;
		this.status = status;
	}
	public Order() {
		
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getId() {
		return id;
	}
}