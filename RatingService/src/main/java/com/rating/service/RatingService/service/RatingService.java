package com.rating.service.RatingService.service;

import com.rating.service.RatingService.entites.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //List all the rating

    List<Rating> getAll();

    //get by userId

    List<Rating> getRatingByUserId(String userId);

    //get by hotelId

    List<Rating> getRatingByHotelId(String hotelId);



}
