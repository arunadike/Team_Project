package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Project3.Project3.model.Review;
import com.Project3.Project3.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/reviewPost")
    public void reviewPost(@RequestBody @Valid Review review) {
        reviewService.saveData(review);
    }

    @GetMapping("/reviewGet")
    public List<Review> reviewGet() {
        return reviewService.returnData();
    }
}