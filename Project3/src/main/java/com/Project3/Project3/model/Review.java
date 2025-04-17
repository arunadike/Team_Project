package com.Project3.Project3.model;

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
    
    private String reviewTimestamp;

    public Review() {
        this.reviewTimestamp = TimeUtil.getCurrentTime(); // Set default value
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

    public String getReviewTimestamp() {
        return reviewTimestamp;
    }

    public void setReviewTimestamp(String reviewTimestamp) {
        this.reviewTimestamp = reviewTimestamp;
    }

    @Override
    public String toString() {
        return "Review [reviewId=" + reviewId + ", user=" + user + ", travelPackage=" + travelPackage + ", rating="
                + rating + ", comment=" + reviewComment + ", reviewTimestamp=" + reviewTimestamp + "]";
    }
}
