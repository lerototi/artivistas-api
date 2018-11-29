package com.artivistas.repository.profilegroup;

import java.util.List;

import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.filter.ProfileGroupFilter;

public interface ProfileGroupRepositoryQuery {

	public List<ProfileGroup> filter(ProfileGroupFilter profileGroupFilter);
	
}
