package com.user.service.UserService.service;

import com.user.service.UserService.entites.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

}
