package com.example.bookmyticket.service;

import java.util.Optional;

import com.example.bookmyticket.models.UsersData;

public interface UserService {

    UsersData signup(UsersData usersData);

    Optional<UsersData> findByEmail(String email);

}