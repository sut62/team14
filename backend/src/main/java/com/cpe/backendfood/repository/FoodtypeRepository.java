package com.cpe.backendfood.repository;
import com.cpe.backendfood.entity.Foodtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface FoodtypeRepository extends JpaRepository<Foodtype, Long> {
	Foodtype findById(long id);
}