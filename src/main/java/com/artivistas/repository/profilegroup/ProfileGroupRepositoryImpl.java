package com.artivistas.repository.profilegroup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.filter.ProfileGroupFilter;

public class ProfileGroupRepositoryImpl implements ProfileGroupRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ProfileGroup> filter(ProfileGroupFilter profileGroupFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ProfileGroup> criteria = builder.createQuery(ProfileGroup.class);
		Root<ProfileGroup> root = criteria.from(ProfileGroup.class);
		
		//Criar as restrições
		Predicate[] predicates = createRestrictions(profileGroupFilter, builder, root);
		criteria.where(predicates);
		
		
		
		TypedQuery<ProfileGroup> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestrictions(ProfileGroupFilter profileGroupFilter, CriteriaBuilder builder,
			Root<ProfileGroup> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(profileGroupFilter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("name")), "%" + profileGroupFilter.getName().toLowerCase() + "%"));
		}
		
		if (profileGroupFilter.getFoundedFrom() != null){			
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("founded"), profileGroupFilter.getFoundedFrom()));
		}
		
		if (profileGroupFilter.getFoundedUntil() != null){
			predicates.add(
					builder.lessThanOrEqualTo(root.get("founded"), profileGroupFilter.getFoundedUntil()));
		}
		
		if (profileGroupFilter.getRegisteredFrom() != null){
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("registered"), profileGroupFilter.getRegisteredFrom()));
		}
		
		if (profileGroupFilter.getRegisteredUntil() != null){
			predicates.add(
					builder.lessThanOrEqualTo(root.get("registered"), profileGroupFilter.getRegisteredUntil()));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
