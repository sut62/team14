package com.cpe.backendfood.controller;

import com.cpe.backendfood.entity.Foodtype;
import com.cpe.backendfood.repository.FoodtypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.200:8081")
@RestController
public class FoodtypeController {

    @Autowired
    private final FoodtypeRepository foodtypeRepository;

    public FoodtypeController(FoodtypeRepository foodtypeRepository) {
        this.foodtypeRepository = foodtypeRepository;
    }

    @GetMapping("/foodtype")
    public Collection<Foodtype> Foodtypes() {
        return foodtypeRepository.findAll().stream().collect(Collectors.toList());
    }

}
