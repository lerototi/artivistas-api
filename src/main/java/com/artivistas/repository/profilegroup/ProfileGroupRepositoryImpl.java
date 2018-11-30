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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.filter.ProfileGroupFilter;

public class ProfileGroupRepositoryImpl implements ProfileGroupRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<ProfileGroup> filter(ProfileGroupFilter profileGroupFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ProfileGroup> criteria = builder.createQuery(ProfileGroup.class);
		Root<ProfileGroup> root = criteria.from(ProfileGroup.class);
		
		//Criar as restrições
		Predicate[] predicates = createRestrictions(profileGroupFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ProfileGroup> query = manager.createQuery(criteria);
		addRestrictionsPage(query, pageable);
		
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(profileGroupFilter));
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
	
	

	private void addRestrictionsPage(TypedQuery<ProfileGroup> query, Pageable pageable) {
		int atualPage = pageable.getPageNumber();
		System.out.println(atualPage);
		int totalRegistryPage = pageable.getPageSize();
		System.out.println(totalRegistryPage);
		int firstRegistryPage = atualPage * totalRegistryPage;
		System.out.println(firstRegistryPage);
		
		query.setFirstResult(firstRegistryPage);
		query.setMaxResults(totalRegistryPage);
		
	}
	
	private Long total(ProfileGroupFilter profileGroupFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ProfileGroup> root =  criteria.from(ProfileGroup.class);
		
		Predicate[] predicates = createRestrictions(profileGroupFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
