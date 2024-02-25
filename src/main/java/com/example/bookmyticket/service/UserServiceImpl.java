package com.example.bookmyticket.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookmyticket.models.UsersData;
import com.example.bookmyticket.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

     @Override
    public UsersData signup(UsersData usersData) {
        if (userRepository.existsByEmail(usersData.getEmail())) {
            logger.info("Duplicate email");
            throw new DuplicateKeyException("Email already exists");
        }
        
        UsersData newUser = new UsersData();
        newUser.setEmail(usersData.getEmail());
        
        newUser.setUsername(usersData.getUsername());
       
        newUser.setPassword(passwordEncoder.encode(usersData.getPassword()));
        return userRepository.save(newUser);
    }

   @Override
public Optional<UsersData>findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    

    
}

