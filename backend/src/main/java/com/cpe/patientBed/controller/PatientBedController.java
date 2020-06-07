package com.cpe.patientBed.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.patientBed.entity.PatientBed;
import com.cpe.patientBed.entity.PatientRoom;
import com.cpe.patientBed.entity.PatientZone;
//import personnel from sopon
import com.cpe.personnel.entity.Personnel;
import com.cpe.patientBed.entity.PhysicalBed;
import com.cpe.patientBed.repository.PatientBedRepository;
import com.cpe.patientBed.repository.PatientRoomRepository;
import com.cpe.patientBed.repository.PatientZoneRepository;
//import personnel from sopon
import com.cpe.personnel.repository.PersonnelRepository;
import com.cpe.patientBed.repository.PhysicalBedRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.200:8081")
@RestController
public class PatientBedController {
    @Autowired
    //   private final VideoRentalRepository videoRentalRepository;
    private final   PatientBedRepository patientBedRepository;
    @Autowired
    private PatientRoomRepository patientRoomRepository;
    @Autowired
    private PatientZoneRepository patientZoneRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private PhysicalBedRepository physicalBedRepository;

    PatientBedController(PatientBedRepository patientBedRepository) {
        this.patientBedRepository = patientBedRepository;
    }

    @GetMapping("/patientBed")
    public Collection<PatientBed> PatientBeds() {
        return patientBedRepository.findAll().stream().collect(Collectors.toList());
    }


    @GetMapping("/SearchPatientBed/{patientBed_id}")   
    public PatientBed SearchBed(@PathVariable("patientBed_id") long patientBed_id) { 
        return patientBedRepository.findById(patientBed_id);
    }

    @PostMapping("/patientBed/{personal_id}/{zone_id}/{room_id}/{physBed_id}/{detail}")
    public PatientBed savePatientBed(PatientBed newPatientBed,
    @PathVariable long personal_id,
    @PathVariable long zone_id,
    @PathVariable long room_id,
    @PathVariable long physBed_id,
    @PathVariable String detail) {
   
//search id
        Personnel createdBy = personnelRepository.findById(personal_id);
        PatientZone atZone = patientZoneRepository.findById(zone_id);
        PatientRoom atRoom = patientRoomRepository.findById(room_id);
        PhysicalBed physicalBed = physicalBedRepository.findById(physBed_id);
        String show =  "  " +atRoom.getRoom_name()+" - "+ atZone.getZone_name();

//set
newPatientBed.setCreatedBy(createdBy);
newPatientBed.setAtZone(atZone);
newPatientBed.setAtRoom(atRoom);
newPatientBed.setPhysicalBed(physicalBed);
newPatientBed.setDetail(detail);
newPatientBed.setShow(show);

//save
    return patientBedRepository.save(newPatientBed); 
   
    
    }
}
