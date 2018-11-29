package com.artivistas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.profilegroup.ProfileGroupRepositoryQuery;

public interface ProfileGroupRepository extends JpaRepository<ProfileGroup, Long>, ProfileGroupRepositoryQuery{

}
