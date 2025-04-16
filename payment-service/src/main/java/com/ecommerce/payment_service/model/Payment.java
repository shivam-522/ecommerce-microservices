package com.ecommerce.payment_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data// this is a lombook annotation in this we will get (Getters and setters, toString())
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	@Id
    private String paymentId;
    private String orderId;
    private String paymentStatus; // SUCCESS, FAILED
    private String paymentMode;   // UPI, CARD, NET_BANKING, etc.
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Payment(String paymentId, String orderId, String paymentStatus, String paymentMode) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.paymentStatus = paymentStatus;
		this.paymentMode = paymentMode;
	}
	Payment()
	{
		
	}
	
}