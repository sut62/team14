package com.cpe.DiseasePackage.repository;

import com.cpe.DiseasePackage.entity.LifeSpan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public
interface LifeSpanRepository extends JpaRepository<LifeSpan, Long> {
	LifeSpan findById(long id);
}