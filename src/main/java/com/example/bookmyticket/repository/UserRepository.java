package com.example.bookmyticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmyticket.models.UsersData;

@Repository
public interface UserRepository extends JpaRepository<UsersData, String> {
    
    Optional<UsersData> findByEmail(String email);
    boolean existsByEmail(String email);
}
