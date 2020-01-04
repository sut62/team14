package com.cpe.patientBed.repository;

import com.cpe.patientBed.entity.PatientZone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PatientZoneRepository extends JpaRepository<PatientZone, Long> {
	PatientZone findById(long zone_id);
}