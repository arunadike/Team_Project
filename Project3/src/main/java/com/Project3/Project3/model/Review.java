package com.Project3.Project3.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="review1")
public class Review {

    @Id
    private int reviewId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "packageId", nullable=false)
    private TravelPackage travelPackage;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
    
    private String reviewComment;
    
    private LocalDateTime reviewTimestamp;

    public Review() {
        this.reviewTimestamp = LocalDateTime.now(); // Set default value
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return reviewComment;
    }

    public void setComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public LocalDateTime getReviewTimestamp() {
        return reviewTimestamp;
    }

    public void setReviewTimestamp(LocalDateTime reviewTimestamp) {
        this.reviewTimestamp = reviewTimestamp;
    }

    
    public Review(int reviewId, User user, TravelPackage travelPackage, @NotNull @Min(1) @Max(5) int rating,
			String reviewComment, LocalDateTime reviewTimestamp) {
		super();
		this.reviewId = reviewId;
		this.user = user;
		this.travelPackage = travelPackage;
		this.rating = rating;
		this.reviewComment = reviewComment;
		this.reviewTimestamp = reviewTimestamp;
	}

	@Override
    public String toString() {
        return "Review [reviewId=" + reviewId + ", user=" + user + ", travelPackage=" + travelPackage + ", rating="
                + rating + ", comment=" + reviewComment + ", reviewTimestamp=" + reviewTimestamp + "]";
    }
}
