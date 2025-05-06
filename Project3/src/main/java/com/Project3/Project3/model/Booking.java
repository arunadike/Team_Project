package com.Project3.Project3.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
	@SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
	private int bookingId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "userid")
	private Users user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "packageId")
	private TravelPackage package1;

	@NotNull
	private Date orderDate;

	@NotNull
	private double price;
	@NotNull
	private String paymentStatus;
	@NotNull
	private String paymentMethod;

	// Constructors
	public Booking() {
	}

	public Booking(int bookingId, @NotNull Users user, @NotNull TravelPackage package1,
				   @NotNull @FutureOrPresent Date orderDate, @NotNull double price, @NotNull String paymentStatus,
				   @NotNull String paymentMethod) {
		this.bookingId = bookingId;
		this.user = user;
		this.package1 = package1;
		this.orderDate = orderDate;
		this.price = price;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}

	// Getters and Setters
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public TravelPackage getPackage1() {
		return package1;
	}

	public void setPackage1(TravelPackage package1) {
		this.package1 = package1;
	}
}