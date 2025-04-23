// Booking Entity
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

@Entity
@Table(name = "booking1")
public class Booking {

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
	@JoinColumn(name = "packageid")  // Foreign key column in Booking table
	private TravelPackage package1;

//	@NotNull
	@FutureOrPresent
	private Date orderDate;

	@NotNull
	private double price;
	@NotNull
	private String paymentStatus;
	@NotNull
	private String paymentMethod;

	public Booking() {

	}
	// Constructors, getters, and setters

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

	public Booking(long bookingId, User user, TravelPackage package1, Date orderDate, double price, String paymentStatus, String paymentMethod) {
		this.bookingId = bookingId;
		this.user = user;
		this.package1 = package1;
		this.orderDate = orderDate;
		this.price = price;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "Booking{" +
				"bookingId=" + bookingId +
				", user=" + user +
				", package1=" + package1 +
				", orderDate=" + orderDate +
				", price=" + price +
				", paymentStatus='" + paymentStatus + '\'' +
				", paymentMethod='" + paymentMethod + '\'' +
				'}';
	}
}