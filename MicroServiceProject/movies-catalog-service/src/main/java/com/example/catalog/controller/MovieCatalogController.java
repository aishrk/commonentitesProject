package com.example.catalog.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.catalog.entity.CatalogIteams;
import com.example.catalog.entity.userRating;
import com.example.catalog.hystrix.CatalogitemsHystrix;
import com.example.catalog.hystrix.UserRatingHystrix;

@RestController
@RequestMapping("/catalogs")
public class MovieCatalogController {

	@Autowired
	private WebClient.Builder webclientBuilder;

	@Autowired
	CatalogitemsHystrix catalogitemsHystrix;

	@Autowired
	UserRatingHystrix userRatingHystrix;

	@RequestMapping("/{userId}")
	public List<CatalogIteams> getCatalogs(@PathVariable("userId") String userId) {

		// get all rated movies Ids
		userRating userRatings = userRatingHystrix.getUserRating(userId);
		return userRatings.getUserRating().stream().map(rating -> {
			return catalogitemsHystrix.getCatalogitems(rating);
		}).collect(Collectors.toList());

	}

}
