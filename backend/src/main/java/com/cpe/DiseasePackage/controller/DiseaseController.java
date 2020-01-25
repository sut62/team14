package com.cpe.DiseasePackage.controller;
import com.cpe.DiseasePackage.repository.*;
import com.cpe.DiseasePackage.entity.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.json.JsonParseException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;
import com.cpe.personnel.entity.Personnel;
import com.cpe.personnel.repository.PersonnelRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class DiseaseController {
    @Autowired
    private  DiseaseRepository diseaseRepository;
    @Autowired
    private  TypeRepository typeRepository;
    @Autowired
    private  LifeSpanRepository lifespanRepository;
    @Autowired
    private  PersonnelRepository personnelRepository;

    DiseaseController( DiseaseRepository diseaseRepository){
            this.diseaseRepository = diseaseRepository;
    }

    @GetMapping("/SearchDis/{id}")   
    public Disease SearchDis(@PathVariable("id") long id) { 
        return diseaseRepository.findById(id);
    }

    @GetMapping("/disease")
    public Collection<Disease> Diseases() {
        return diseaseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/disease/{name}/{symptom}/{type_id}/{lifespan_id}/{personnel_id}")
    public Disease newDisease( Disease newDisease,
    @PathVariable  String name,
    @PathVariable  String symptom,
    @PathVariable  long type_id,
    @PathVariable  long lifespan_id,
    @PathVariable  long personnel_id
    )
    {

      Type type = typeRepository.findById(type_id);
      LifeSpan lifespan = lifespanRepository.findById(lifespan_id);
      Personnel personnel = personnelRepository.findById(personnel_id);

     newDisease.setName(name);
     newDisease.setSymptom(symptom);
     newDisease.setType(type);
     newDisease.setLifespan(lifespan);
     newDisease.setPersonnel(personnel);

     return diseaseRepository.save(newDisease);
    }

    
}
