package com.Project3.Project3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="travelpackage1")
public class TravelPackage {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_package_seq")
	@SequenceGenerator(name = "travel_package_seq", sequenceName = "TRAVEL_PACKAGE_SEQ", allocationSize = 1)
	private int packageId;
	@NotNull
	private String title;
	private String description;
	@NotNull
	private int duration; // Duration in days
	private double price;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	private String includedService;
	@ManyToOne
	@JoinColumn(name="userid")
	private Users user;
	private String imageUrl;

public TravelPackage(int packageId, @NotNull String title, String description, @NotNull int duration, double price,
			String includedService, Users user, String imageUrl) {
		super();
		this.packageId = packageId;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.includedService = includedService;
		this.user = user;
		this.imageUrl = imageUrl;
	}
	public TravelPackage()
	{

	}



	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIncludedService() {
		return includedService;
	}
	public void setIncludedService(String includedService) {
		this.includedService = includedService;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "TravelPackage [packageId=" + packageId + ", title=" + title + ", description=" + description
				+ ", duration=" + duration + ", price=" + price + ", includedService=" + includedService + ", user="
				+ user + ", imageUrl=" + imageUrl + "]";
	}


}
