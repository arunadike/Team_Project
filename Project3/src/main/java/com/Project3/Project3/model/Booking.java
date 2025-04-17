package com.Project3.Project3.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	private long bookingId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@NotNull
	@OneToOne
	@JoinColumn(name = "packageid")
	private TravelPackage package1;

	@NotNull
	@FutureOrPresent
	private Date startDate;

	@NotNull
	@FutureOrPresent
	private Date endDate;

	@NotNull
	private String status;

	@NotNull
	@OneToOne
	@JoinColumn(name = "paymentid")
	private Payment1 payment;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Payment1 getPayment() {
		return payment;
	}

	public void setPayment(Payment1 payment) {
		this.payment = payment;
	}
	
	public Booking() {
		
	}

	public Booking(long bookingId, @NotNull User user, @NotNull TravelPackage package1,
			@NotNull @FutureOrPresent Date startDate, @NotNull @FutureOrPresent Date endDate,
			@NotNull @Size(min = 0, max = 2) String status, @NotNull Payment1 payment) {
		// super();
		this.bookingId = bookingId;
		this.user = user;
		this.package1 = package1;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", user=" + user + ", package1=" + package1 + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + ", payment=" + payment + "]";
	}

}
