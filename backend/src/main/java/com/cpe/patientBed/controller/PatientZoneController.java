package com.cpe.patientBed.controller;

import com.cpe.patientBed.entity.PatientZone;
import com.cpe.patientBed.repository.PatientZoneRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PatientZoneController {

    @Autowired
    //private final PatientZoneRepository patientZoneRepository;
    private  final PatientZoneRepository patientZoneRepository;

    public PatientZoneController(PatientZoneRepository patientZoneRepository) {
        this.patientZoneRepository = patientZoneRepository;
    }

    @GetMapping("/patientZone")
    public Collection<PatientZone> PatientZones() {
        return patientZoneRepository.findAll().stream().collect(Collectors.toList());
    }

}