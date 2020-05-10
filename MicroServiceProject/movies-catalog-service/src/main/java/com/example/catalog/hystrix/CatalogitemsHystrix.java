package com.example.catalog.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.catalog.entity.CatalogIteams;
import com.example.catalog.entity.Movies;
import com.example.catalog.entity.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CatalogitemsHystrix {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogitems")
	public CatalogIteams getCatalogitems(Rating rating) {
		Movies movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
				Movies.class);
		return new CatalogIteams(movie.getName(), movie.getDescription(), rating.getRating());
	}

	// fallback method for getCatalogitems
	public CatalogIteams getFallbackCatalogitems(Rating rating) {
		return new CatalogIteams("Movie Not found ", "", rating.getRating());
	}
}
