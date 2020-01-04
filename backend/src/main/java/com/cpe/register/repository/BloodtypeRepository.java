package com.cpe.register.repository;

import com.cpe.register.entity.Bloodtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BloodtypeRepository extends JpaRepository<Bloodtype, Long> {
    Bloodtype findById(long id);
}