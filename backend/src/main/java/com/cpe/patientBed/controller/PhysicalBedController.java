package com.cpe.patientBed.controller;

import com.cpe.patientBed.entity.PhysicalBed;
import com.cpe.patientBed.repository.PhysicalBedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.200:8081")
@RestController
public class PhysicalBedController {

    @Autowired
    //    private final PhysicalBedRepository physicalBedRepository;
    private final   PhysicalBedRepository physicalBedRepository;

    public PhysicalBedController(PhysicalBedRepository physicalBedRepository) {
        this.physicalBedRepository = physicalBedRepository;
    }

    @GetMapping("/physicalBed")
    public Collection<PhysicalBed> PhysicalBeds() {
        return physicalBedRepository.findAll().stream().collect(Collectors.toList());
    }

}
