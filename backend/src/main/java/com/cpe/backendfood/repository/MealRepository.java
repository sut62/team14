package com.cpe.backendfood.repository;
import com.cpe.backendfood.entity.Meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MealRepository extends JpaRepository<Meal, Long> {
	Meal findById(long id);
}