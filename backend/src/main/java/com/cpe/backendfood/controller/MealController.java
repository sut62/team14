package com.cpe.backendfood.controller;

import com.cpe.backendfood.entity.Meal;
import com.cpe.backendfood.repository.MealRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.200:8081")
@RestController
public class MealController {

    @Autowired
    private final MealRepository mealRepository;

    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @GetMapping("/meal")
    public Collection<Meal> Meals() {
        return mealRepository.findAll().stream().collect(Collectors.toList());
    }

}
