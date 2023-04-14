package com.user.service.UserService.controllers;

import com.user.service.UserService.entites.User;
import com.user.service.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    //create user
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable ("userId") String userId) {
        User getUser = userService.getUser(userId);
        return ResponseEntity.ok(getUser);
    }

    // Creating fallback method when a service is fail anf circuit is breaks.
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback in executed because service is down : ", ex.getMessage());
        User user = User.builder()
                .name("Dummy")
                .email("Dummy@DumDum.Dum")
                .about("This user is created dummy because some service are down")
                .userId("1987287")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    //list of users
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

}
