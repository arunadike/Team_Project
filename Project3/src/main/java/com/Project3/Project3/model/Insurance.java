package com.Project3.Project3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "INSURANCE")
public class Insurance {

	@Id
	private int insuranceId;

	@NotNull
	private String coverageDetails;

	@NotNull
	private String provider;

	@NotNull
	private double amount;

	@OneToOne
	@JoinColumn(name = "paymentId", nullable = false)
	private Payment payment;

	@NotNull
	private String status;

	@OneToOne
	@JoinColumn(name = "bookingId", nullable = false)
	private Booking booking;

	@OneToOne
	@JoinColumn(name = "userid", nullable = false)
	private Users user;

	public Insurance() {
		this.status = "ACTIVE";
	}
	
	public Insurance(int insuranceId, @NotNull String coverageDetails, @NotNull String provider, @NotNull double amount,
			Payment payment, String status, Booking booking, Users user) {
		super();
		this.insuranceId = insuranceId;
		this.coverageDetails = coverageDetails;
		this.provider = provider;
		this.amount = amount;
		this.payment = payment;
		this.status = status;
		this.booking = booking;
		this.user = user;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getCoverageDetails() {
		return coverageDetails;
	}

	public void setCoverageDetails(String coverageDetails) {
		this.coverageDetails = coverageDetails;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Payment getPayment1() {
		return payment;
	}

	public void setPayment1(Payment payment1) {
		this.payment = payment1;
	}
	
	@Override
	public String toString() {
		return "Insurance [insuranceId=" + insuranceId + ", coverageDetails=" + coverageDetails + ", provider="
				+ provider + ", status=" + status + ", user=" + user + "]";
	}

}