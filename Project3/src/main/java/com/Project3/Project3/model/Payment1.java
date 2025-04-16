package com.Project3.Project3.model;

import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="payment1")
public class Payment1 {
	public Payment1(int paymentId, @NotNull double amount, @NotNull String status, @NotNull String paymentMethod,
			TravelPackage travelPackage, User user) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.travelPackage = travelPackage;
		this.user = user;
	}
	public Payment1()
	{
		
	}
	
	
	@Id
	private int paymentId;
	@NotNull
	private double amount;
	@NotNull
	private String status; 
	@NotNull
	private String paymentMethod;
	@ManyToOne
    @JoinColumn(name = "packageId",nullable=false) 
	private TravelPackage travelPackage;
	@ManyToOne
	@JoinColumn(name="userid",nullable=false)
	private User user;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public TravelPackage getTravelPackage() {
		return travelPackage;
	}
	public void setTravelPackage(TravelPackage travelPackage) {
		this.travelPackage = travelPackage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Payment1 [paymentId=" + paymentId + ", amount=" + amount + ", status=" + status + ", paymentMethod="
				+ paymentMethod + "]";
	}
	

}
