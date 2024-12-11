package com.ecommerce.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PaymentDTO {
	
    private String paymentId;
    private String paymentMethod;
    private String pgStatus;
    private String pgResponseMessage;
    private String pgName;
    
    
	public PaymentDTO(String paymentId, String paymentMethod, String pgStatus,
			String pgResponseMessage, String pgName) {
		super();
		this.paymentId = paymentId;
		this.paymentMethod = paymentMethod;
		this.pgStatus = pgStatus;
		this.pgResponseMessage = pgResponseMessage;
		this.pgName = pgName;
	}
	
	
	
	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
    
    
}

