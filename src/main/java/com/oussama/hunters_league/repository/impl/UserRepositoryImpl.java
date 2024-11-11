package com.oussama.hunters_league.repository.impl;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.repository.UserRepository;
import com.oussama.hunters_league.web.vm.user.SearchDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl{
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    public List<User> findByCriteria(SearchDTO searchDTO){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> userRoot=criteriaQuery.from(User.class);

        List<Predicate> predicateList=new ArrayList<>();
        if(searchDTO.getUsername()!=null && !searchDTO.getUsername().isEmpty())
            predicateList.add(criteriaBuilder.like(userRoot.get("username"),"%"+searchDTO.getUsername()));
        if(searchDTO.getFirstName()!=null && !searchDTO.getFirstName().isEmpty())
            predicateList.add(criteriaBuilder.like(userRoot.get("firstName"),"%"+searchDTO.getFirstName()));
        if(searchDTO.getLastName()!=null && !searchDTO.getLastName().isEmpty())
            predicateList.add(criteriaBuilder.like(userRoot.get("lastName"),"%"+searchDTO.getLastName()));

        criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
