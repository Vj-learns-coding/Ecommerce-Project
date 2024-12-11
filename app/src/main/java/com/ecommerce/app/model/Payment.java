package com.ecommerce.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="payments")
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	
	@Size(min=4,message="payment method should atleast containe 4 characters")
	private String paymentMethod;
	
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	private String pgName;
	private String pgPaymentId;
	private String pgStatus;
	private String pgResponseMessage;
	
	

	
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Payment(String paymentMethod2, String pgPaymentId2, String pgStatus2, String pgResponseMessage2,String pgName2) {
		this.pgPaymentId = pgPaymentId2;
		this.paymentMethod = paymentMethod2;
		this.pgStatus = pgStatus2;
		this.pgResponseMessage = pgResponseMessage2;
		this.pgName=pgName2;
	}


	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPgPaymentId() {
		return pgPaymentId;
	}


	public void setPgPaymentId(String pgPaymentId) {
		this.pgPaymentId = pgPaymentId;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	
	public String getPgStatus() {
		return pgStatus;
	}
	public void setPgStatus(String pgStatus) {
		this.pgStatus = pgStatus;
	}
	public String getPgResponseMessage() {
		return pgResponseMessage;
	}
	public void setPgResponseMessage(String pgResponseMessage) {
		this.pgResponseMessage = pgResponseMessage;
	}
	
	
	
}
