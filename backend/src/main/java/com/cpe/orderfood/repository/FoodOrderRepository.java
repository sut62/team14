package com.cpe.orderfood.repository;

import com.cpe.orderfood.entity.FoodOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    FoodOrder findById(long id);
}