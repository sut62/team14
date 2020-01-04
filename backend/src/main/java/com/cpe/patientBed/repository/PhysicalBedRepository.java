package com.cpe.patientBed.repository;

import com.cpe.patientBed.entity.PhysicalBed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PhysicalBedRepository extends JpaRepository<PhysicalBed, Long> {
	PhysicalBed findById(long physBed_id);//search at PhysicalBed Entity
}