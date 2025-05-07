package com.Project3.Project3.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cartitems", uniqueConstraints = { @UniqueConstraint(columnNames = { "userid", "packageid" }) })
public class CartItems {
	public CartItems(Integer cartItemID, @NotNull Users user, @NotNull TravelPackage package1,
			@NotNull @FutureOrPresent Date startDate, @NotNull int noOfPersons, boolean insurance,
			@NotNull double price) {
		super();
		this.cartItemID = cartItemID;
		this.user = user;
		this.package1 = package1;
		this.startDate = startDate;
		this.noOfPersons = noOfPersons;
		this.insurance = insurance;
		this.price = price;

	}

	public CartItems() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_items_seq")
	@SequenceGenerator(name = "cart_items_seq", sequenceName = "CART_ITEMS_SEQ", allocationSize = 1)
	@Column(name = "CartItemID")
	private Integer cartItemID;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "userid")
	private Users user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "packageid")
	private TravelPackage package1;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Asia/Kolkata")
	private Date startDate;

	@NotNull
	private int noOfPersons;

	private boolean insurance;

	@NotNull
	private double price;

	public Integer getCartItemID() {
		return cartItemID;
	}

	public void setCartItemID(Integer cartItemID) {
		this.cartItemID = cartItemID;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
