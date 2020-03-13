package com.cpe.register.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.register.entity.Bloodtype;
import com.cpe.register.entity.Register;
import com.cpe.register.entity.Gender;
import com.cpe.personnel.entity.Personnel;
import com.cpe.DiseasePackage.entity.Disease;
import com.cpe.patientBed.entity.PatientBed;

import com.cpe.register.repository.RegisterRepository;
import com.cpe.register.repository.BloodtypeRepository;
import com.cpe.register.repository.GenderRepository;
import com.cpe.personnel.repository.PersonnelRepository;
import com.cpe.DiseasePackage.repository.DiseaseRepository;
import com.cpe.patientBed.repository.PatientBedRepository;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RegisterController {
    @Autowired
    private final RegisterRepository registerRepository;
    @Autowired
    private BloodtypeRepository bloodtypeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private PatientBedRepository patientBedRepository;
    
    RegisterController(final RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping("/register")
    public Collection<Register> Registers() {
        return registerRepository.findAll().stream().collect(Collectors.toList());
    }
    //ใส่พาท
    @PostMapping("/register{personnel_id}/{addname}/{addlastname}/{addage}/{addtelephone}/{bloodtype_id}/{gender_id}/{disease_id}/{patientBed_id}")
    public Register newRegister(final Register newRegister,
    @PathVariable final String addname,
    @PathVariable final String addlastname,
    @PathVariable final Integer addage,
    @PathVariable final String addtelephone,
    @PathVariable final long gender_id,
    @PathVariable final long bloodtype_id,
    @PathVariable final long personnel_id,
    @PathVariable final long disease_id,
    @PathVariable final long patientBed_id
    ){            

        final Bloodtype   bloodtypename   = bloodtypeRepository.findById(bloodtype_id);
        final Gender      gendername      = genderRepository.findById(gender_id);
        final Personnel   createdby       = personnelRepository.findById(personnel_id);
        final Disease     desname         = diseaseRepository.findById(disease_id);
        final PatientBed  patname         = patientBedRepository.findById(patientBed_id);

    newRegister.setAddname(addname);
    newRegister.setAddlastname(addlastname);
    newRegister.setAddage(addage);
    newRegister.setTelephone(addtelephone);
    newRegister.setBloodtypename(bloodtypename);
    newRegister.setGendername(gendername);
    newRegister.setCreatedby(createdby);
    newRegister.setDesname(desname);
    newRegister.setPatname(patname);

    return registerRepository.save(newRegister);
    
    }
}