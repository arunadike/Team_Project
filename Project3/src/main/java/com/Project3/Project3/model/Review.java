package com.Project3.Project3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name="review")
public class Review {

    public Review(){
        super();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @SequenceGenerator(name = "review_seq", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private int id;

    @OneToOne
    @JoinColumn(name = "booking_id", unique = true)
    private Booking booking;

    private String reviewComment;
    private int rating;
    private Date reviewDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "packageId")
    private TravelPackage package1;
    public int getId() {
        return id;
    }

    public Review(int id, Booking booking, String reviewComment, int rating, Date reviewDate, TravelPackage package1) {
        this.id = id;
        this.booking = booking;
        this.reviewComment = reviewComment;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.package1 = package1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public TravelPackage getPackage1() {
        return package1;
    }

    public void setPackage1(TravelPackage package1) {
        this.package1 = package1;
    }

	@Override
	public String toString() {
		return "Review{" + "id=" + id + ", booking=" + booking + ", reviewComment='" + reviewComment + '\''
				+ ", rating=" + rating + ", reviewDate=" + reviewDate + ", package1=" + package1 + '}';
	}
}