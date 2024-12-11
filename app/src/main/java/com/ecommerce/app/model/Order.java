package com.ecommerce.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")

public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderId;
	
	@Email
	@Column(nullable=false)
	private String email;
	
	@OneToOne
	@JoinColumn(name="payment_id")
	private Payment payment;
	
	private LocalDate orderDate;
	
	@OneToMany(mappedBy="order",cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	private List<OrderItem> orderItems= new ArrayList<>();
	
	private double totalAmount;
	
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name="Address_Id")
	private Address address;

	public Order(long orderId, @Email String email, Payment payment, LocalDate orderDate, List<OrderItem> orderItems,
			double totalAmount, String orderStatus, Address address) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.payment = payment;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.address = address;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
