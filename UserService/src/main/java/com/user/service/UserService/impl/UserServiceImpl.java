package com.user.service.UserService.impl;

import com.user.service.UserService.entites.Hotel;
import com.user.service.UserService.entites.Rating;
import com.user.service.UserService.entites.User;
import com.user.service.UserService.exception.ResourceNotFoundException;
import com.user.service.UserService.external.services.HotelService;
import com.user.service.UserService.repo.UserRepository;
import com.user.service.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserID = UUID.randomUUID().toString();
        user.setUserId(randomUserID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //getting detail of one user only

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id did now found: " + userId));

        //fetching the data from rating service to find out the rating provided by user
        // http://localhost:8083/ratings/users/c3a577f6-da6b-4ddb-857d-5cb2da4887ef

        Rating[] ratingByUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingByUser);

        List<Rating> list = Arrays.stream(ratingByUser).toList();

        List<Rating> ratingList = list.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/2936bc17-5c1d-40df-9df9-1abf3c6191f5

//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

//            logger.info("Response Code: {} ", forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;

        }).collect(Collectors.toList());
        user.setRating(ratingList);
        return user;
    }
}
