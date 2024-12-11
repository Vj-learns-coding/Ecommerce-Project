package com.ecommerce.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderRequestDTO {
	
    private long addressId;
    private String paymentMethod;
    private String pgName;
    private String pgPaymentId;
    private String pgStatus;
    private String pgResponseMessage;
    
	public OrderRequestDTO(Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus,
			String pgResponseMessage) {
		super();
		this.addressId = addressId;
		this.paymentMethod = paymentMethod;
		this.pgName = pgName;
		this.pgPaymentId = pgPaymentId;
		this.pgStatus = pgStatus;
		this.pgResponseMessage = pgResponseMessage;
	}
	
	public OrderRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	public String getPgPaymentId() {
		return pgPaymentId;
	}
	public void setPgPaymentId(String pgPaymentId) {
		this.pgPaymentId = pgPaymentId;
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

	@Override
	public String toString() {
		return "OrderRequestDTO [addressId=" + addressId + ", paymentMethod=" + paymentMethod + ", pgName=" + pgName
				+ ", pgPaymentId=" + pgPaymentId + ", pgStatus=" + pgStatus + ", pgResponseMessage=" + pgResponseMessage
				+ "]";
	}
	
    
    
}
