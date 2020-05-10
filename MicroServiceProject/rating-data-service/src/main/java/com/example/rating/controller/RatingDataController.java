package com.example.rating.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rating.entity.Rating;
import com.example.rating.entity.userRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataController {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieid) {
		return new Rating(movieid,5);
	
	}

	@RequestMapping("/user/{userid}")
	public userRating getUserId(@PathVariable("userid") String movieid) {
		
		List<Rating> ratings=Arrays.asList(
				new Rating("100",8),
				new Rating("200",9),
				new Rating("300",7),
				new Rating("400",9)
				);
		userRating userRating = new userRating(movieid, ratings);
		return userRating;
	}
	

}
