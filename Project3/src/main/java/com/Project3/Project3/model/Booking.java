package com.Project3.Project3.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "booking1")
public class Booking {

	public Booking(long bookingId, @NotNull User user, @NotNull TravelPackage package1,
			@NotNull @FutureOrPresent Date orderDate, @NotNull double price, @NotNull String paymentStatus,
			@NotNull String paymentMethod) {
		super();
		this.bookingId = bookingId;
		this.user = user;
		this.package1 = package1;
		this.orderDate = orderDate;
		this.price = price;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
	private long bookingId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@NotNull
	@OneToOne
	@JoinColumn(name = "packageId",nullable = false)
	private TravelPackage package1;

	@NotNull
	@FutureOrPresent
	private Date orderDate;

//	@NotNull
//	@FutureOrPresent
//	private Date endDate;

	@NotNull
	private double price;
	@NotNull
	private String paymentStatus;
	@NotNull
	private String paymentMethod;

//	@NotNull
//	@OneToOne
//	@JoinColumn(name = "paymentid")
//	private Payment1 payment;

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

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TravelPackage getPackage1() {
		return package1;
	}

	public void setPackage1(TravelPackage package1) {
		this.package1 = package1;
	}








	public Booking() {

	}

//	public Booking(long bookingId, @NotNull User user, @NotNull TravelPackage package1,
//			@NotNull @FutureOrPresent Date startDate, @NotNull @FutureOrPresent Date endDate,
//			@NotNull @Size(min = 0, max = 2) String status, @NotNull Payment1 payment) {
//		// super();
//		this.bookingId = bookingId;
//		this.user = user;
//		this.package1 = package1;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.status = status;
//		this.payment = payment;
//	}

//	@Override
//	public String toString() {
//		return "Booking [bookingId=" + bookingId + ", user=" + user + ", package1=" + package1 + ", startDate="
//				+ startDate + ", endDate=" + endDate + ", status=" + status + ", payment=" + payment + "]";
//	}

}
