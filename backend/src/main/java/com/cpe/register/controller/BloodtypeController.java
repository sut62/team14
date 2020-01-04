package com.cpe.register.controller;
import com.cpe.register.entity.Bloodtype;
import com.cpe.register.repository.BloodtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BloodtypeController {

    @Autowired
    private final BloodtypeRepository bloodtypeRepository;
    public BloodtypeController(BloodtypeRepository  bloodtypeRepository) {
        this.bloodtypeRepository =  bloodtypeRepository;
    }
    @GetMapping("/bloodtype")
    public Collection<Bloodtype> Bloodtypes() {
        return  bloodtypeRepository.findAll().stream().collect(Collectors.toList());
    }
}