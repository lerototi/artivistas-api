package com.artivistas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.ProfileUser;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, Long> {

}
