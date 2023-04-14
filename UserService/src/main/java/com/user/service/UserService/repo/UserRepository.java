package com.user.service.UserService.repo;

import com.user.service.UserService.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
