package com.cpe.patientBed.repository;

import com.cpe.patientBed.entity.PatientBed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public
interface PatientBedRepository extends JpaRepository<PatientBed, Long> {
    PatientBed findById(long patient_id);

}