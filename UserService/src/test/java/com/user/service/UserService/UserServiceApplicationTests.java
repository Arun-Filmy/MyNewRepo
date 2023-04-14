package com.user.service.UserService;

import com.user.service.UserService.entites.Rating;
import com.user.service.UserService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private RatingService ratingService;
//
//	@Test
//	void createRating(){
//		Rating rating = Rating.builder()
//				.userId("15e3d758-6c1a-4ea1-9a8b-6c71de908c1d")
//				.hotelId("a4f7ce8b-7520-4c04-8204-09b0a7ae660d")
//				.remarks("It's a very nice place, I stay here with my family, family friendly hotel.")
//				.rating(10)
//				.build();
//
//		Rating savedRating = ratingService.createRating(rating);
//		System.out.println("New Rating Created");
//
//	}

}
