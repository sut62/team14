package com.cpe.DiseasePackage.repository;

//import java.util.Collection;

import com.cpe.DiseasePackage.entity.Disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public
interface DiseaseRepository extends JpaRepository<Disease, Long> {

	
	
}