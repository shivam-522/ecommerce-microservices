package com.ecommerce.order_service.model.client;

public class InventoryResponse {
    private Long productId;
    //private boolean inStock;
    private int quantity;
    // Getters & setters
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
