package com.artivistas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
