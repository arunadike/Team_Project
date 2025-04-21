package com.Project3.Project3.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cartitems")
public class CartItems {
	public CartItems() {
    }
	
	

    public CartItems(Integer cartItemID, @NotNull User user, @NotNull TravelPackage package1,
			@NotNull @FutureOrPresent Date startDate, @NotNull @Size(min = 1, max = 10) int noOfPersons,
			boolean insurance, @NotNull double price) {
		super();
		this.cartItemID = cartItemID;
		this.user = user;
		this.package1 = package1;
		this.startDate = startDate;
		this.noOfPersons = noOfPersons;
		this.insurance = insurance;
		this.price = price;
		//version=1;
	}



	@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartItemID")
    private Integer cartItemID;

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



	@Override
	public String toString() {
		return "CartItems [cartItemID=" + cartItemID + ", user=" + user + ", package1=" + package1 + ", startDate="
				+ startDate + ", noOfPersons=" + noOfPersons + ", insurance=" + insurance + ", price=" + price + "]";
	}

	

	
}
