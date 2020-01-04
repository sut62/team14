package com.cpe.DiseasePackage.controller;

import com.cpe.DiseasePackage.entity.LifeSpan;
import com.cpe.DiseasePackage.repository.LifeSpanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LifeSpanController {

    @Autowired
    private final LifeSpanRepository lifespanRepository;

    public LifeSpanController(LifeSpanRepository lifespanRepository) {
        this.lifespanRepository = lifespanRepository;
    }

    @GetMapping("/lifespan")
    public Collection<LifeSpan> LifeSpans() {
        return lifespanRepository.findAll().stream().collect(Collectors.toList());
    }
   

} 