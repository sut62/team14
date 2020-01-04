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

import com.cpe.register.repository.RegisterRepository;
import com.cpe.register.repository.BloodtypeRepository;
import com.cpe.register.repository.GenderRepository;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegisterController {
    @Autowired
    private final RegisterRepository registerRepository;
    @Autowired
    private BloodtypeRepository bloodtypeRepository;
    @Autowired
    private GenderRepository genderRepository;
    
    RegisterController(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping("/register")
    public Collection<Register> Registers() {
        return registerRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/register/{addname}/{addlastname}/{addage}/{addtelephone}/{bloodtype_id}/{gender_id}")
    public Register newRegister(Register newRegister,
    @PathVariable String addname,
    @PathVariable String addlastname,
    @PathVariable String addage,
    @PathVariable String addtelephone,
    @PathVariable long gender_id,
    @PathVariable long bloodtype_id){            

        Bloodtype   bloodtypename = bloodtypeRepository.findById(bloodtype_id);                   
        Gender      gendername = genderRepository.findById(gender_id);                                                                                       
    newRegister.setAddname(addname);
    newRegister.setAddlastname(addlastname);
    newRegister.setAddage(addage);
    newRegister.setTelephone(addtelephone);
    newRegister.setBloodtypename(bloodtypename);
    newRegister.setGendername(gendername);

    return registerRepository.save(newRegister);
    
    }
}