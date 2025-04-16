package com.Project3.Project3.model;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="payment1")
public class Payment {
	public Payment()
	{
		
	}
	
	
	@Id
	private int paymentId;
	@NotNull
	private double amount;
	@NotNull
//	@Value("${default.payment.status:PENDING}")
	private String status;
	@NotNull
//	@Value("${default.payment.paymentMethod:Credit}")
	private String paymentMethod;
	@ManyToOne
    @JoinColumn(name = "packageId",nullable=false) 
	private TravelPackage travelPackage;
	@ManyToOne
	@JoinColumn(name="userid",nullable=false)
	private User user;
	
	public Payment(int paymentId, double amount,String status, String paymentMethod,
			TravelPackage travelPackage, User user) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.travelPackage = travelPackage;
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", status=" + status + ", paymentMethod="
				+ paymentMethod + ", travelPackage=" + travelPackage + ", user=" + user + "]";
	}
	
	
	
}
