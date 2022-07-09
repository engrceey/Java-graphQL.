package com.zurum.graphql.repository;

import com.zurum.graphql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByEmail(String email);
}
