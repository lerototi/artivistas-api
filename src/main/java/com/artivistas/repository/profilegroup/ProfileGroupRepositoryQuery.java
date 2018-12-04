package com.artivistas.repository.profilegroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.filter.ProfileGroupFilter;

public interface ProfileGroupRepositoryQuery {

	public Page<ProfileGroup> filter(ProfileGroupFilter profileGroupFilter, Pageable pageable);
	
}
