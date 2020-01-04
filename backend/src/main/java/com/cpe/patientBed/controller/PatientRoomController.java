package com.cpe.patientBed.controller;

import com.cpe.patientBed.entity.PatientRoom;
import com.cpe.patientBed.repository.PatientRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PatientRoomController {

    @Autowired
    //private final PatientRoomRepository patientRoomRepository;
    private final  PatientRoomRepository patientRoomRepository;

    public PatientRoomController(PatientRoomRepository patientRoomRepository) {
        this.patientRoomRepository = patientRoomRepository;
    }

    @GetMapping("/patientRoom")
    public Collection<PatientRoom> PatientRooms() {
        return patientRoomRepository.findAll().stream().collect(Collectors.toList());
    }

}