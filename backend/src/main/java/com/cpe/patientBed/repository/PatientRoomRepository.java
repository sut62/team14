package com.cpe.patientBed.repository;

import com.cpe.patientBed.entity.PatientRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PatientRoomRepository extends JpaRepository<PatientRoom, Long> {
	PatientRoom findById(long room_id);
}