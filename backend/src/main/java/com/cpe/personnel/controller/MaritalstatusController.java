package com.cpe.personnel.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import com.cpe.personnel.entity.Maritalstatus;
import com.cpe.personnel.repository.MaritalstatusRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.202:8081")
@RestController
public class MaritalstatusController {

    @Autowired
    private final MaritalstatusRepository maritalstatusRepository;

    public MaritalstatusController(MaritalstatusRepository maritalstatusRepository) {
        this.maritalstatusRepository = maritalstatusRepository;
    }

    @GetMapping("/maritalstatus")
    public Collection<Maritalstatus> Maritalstatuss() {
        return maritalstatusRepository.findAll().stream().collect(Collectors.toList());
    }

}
