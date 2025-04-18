package com.Project3.Project3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="travelpackage")
public class TravelPackage {
	public TravelPackage()
	{
		
	}
	public TravelPackage(int packageId, @NotNull String title, String description, @NotNull int duration, double price,
			String includedService) {
		super();
		this.packageId = packageId;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.includedService = includedService;
	}
	@Id
	private int packageId;
	@NotNull
    private String title;
    private String description;
    @NotNull
    private int duration; // Duration in days
    private double price;
    private String includedService;
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
	@Override
	public String toString() {
		return "TravelPackage [packageId=" + packageId + ", title=" + title + ", description=" + description
				+ ", duration=" + duration + ", price=" + price + ", includedService=" + includedService + "]";
	}
    

}
