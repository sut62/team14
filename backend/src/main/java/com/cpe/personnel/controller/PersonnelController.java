package com.cpe.personnel.controller;

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

import com.cpe.personnel.entity.Position;
import com.cpe.personnel.entity.Educationlevel;
import com.cpe.personnel.entity.Maritalstatus;
import com.cpe.personnel.entity.Personnel;

import com.cpe.personnel.repository.PersonnelRepository;
import com.cpe.personnel.repository.PositionRepository;
import com.cpe.personnel.repository.EducationlevelRepository;
import com.cpe.personnel.repository.MaritalstatusRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PersonnelController {
    @Autowired
    private final PersonnelRepository personnelRepository;
    @Autowired
    private MaritalstatusRepository maritalstatusRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private EducationlevelRepository educationlevelRepository;
    

    PersonnelController(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @GetMapping("/personnel")
    public Collection<Personnel> Personnels() {
        return personnelRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/personnel/{addfirstname}/{addlastname}/{addtelephone}/{addbirthday}/{maritalstatus_id}/{position_id}/{educationlevel_id}")
    public Personnel newPersonnel(Personnel newPersonnel,
    @PathVariable String addfirstname,
    @PathVariable String addlastname,
    @PathVariable String addtelephone,
    @PathVariable String addbirthday,
    @PathVariable long maritalstatus_id,
    @PathVariable long position_id,
    @PathVariable long educationlevel_id){         
    LocalDate born = LocalDate.parse(addbirthday);                    
    Maritalstatus status = maritalstatusRepository.findById(maritalstatus_id); 
    Position posit = positionRepository.findById(position_id);                                            
    Educationlevel level = educationlevelRepository.findById(educationlevel_id);                           
    
    newPersonnel.setFirstname(addfirstname);
    newPersonnel.setLastname(addlastname);
    newPersonnel.setTelephone(addtelephone);
    newPersonnel.setBirthday(born);
    newPersonnel.setStatus(status);
    newPersonnel.setPosit(posit);
    newPersonnel.setLevel(level);
    return personnelRepository.save(newPersonnel);
    
    }
}