package com.example.bookmyticket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyticket.dto.UserDto;
import com.example.bookmyticket.models.UsersData;
import com.example.bookmyticket.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //postman-api- 1
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UsersData usersData) {
        try {
            UsersData createdUser = userService.signup(usersData);
            return ResponseEntity.ok("UsersData added" + createdUser.getUsername());
        } catch (DuplicateKeyException e) {
            logger.info("Duplicate email - Signup controller");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists. Please use a different email or just login");
        } catch (RuntimeException e) {
            logger.error("Error during signup:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during signup.");
        }
    }

    //postman-api-2
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        UsersData usersData = userService.findByEmail(userDto.getEmail()).get();
        if (usersData == null || !passwordEncoder.matches(userDto.getPassword(), usersData.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // generate a JWT token here and return it to the client for authentication
        return ResponseEntity.ok(usersData.getId());
    }
}