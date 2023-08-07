package com.powerconsuption.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerconsuption.com.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User>findByEmail(String email);

}
