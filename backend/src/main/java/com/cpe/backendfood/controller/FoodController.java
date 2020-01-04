package com.cpe.backendfood.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;


import com.cpe.backendfood.entity.Food;
import com.cpe.backendfood.entity.Foodtype;
import com.cpe.backendfood.entity.Meal;

import com.cpe.personnel.entity.Personnel;


import com.cpe.backendfood.repository.FoodRepository;
import com.cpe.backendfood.repository.FoodtypeRepository;
import com.cpe.backendfood.repository.MealRepository;

import com.cpe.personnel.repository.PersonnelRepository;



import java.time.LocalDate;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class FoodController {
    @Autowired
    private final FoodRepository foodRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private FoodtypeRepository foodtypeRepository;
    @Autowired
    private MealRepository mealRepository;
    
    

    FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping("/food")
    public Collection<Food> Foods() {
        return foodRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/food/{name}/{personnelid}/{foodtypeid}/{mealid}")
    public Food newFood(Food newFood,
    @PathVariable String name,
    @PathVariable long personnelid,
    @PathVariable long foodtypeid,
    @PathVariable long mealid){

    Personnel createdby = personnelRepository.findById(personnelid);
    Foodtype typeby = foodtypeRepository.findById(foodtypeid);                    
    Meal mealby = mealRepository.findById(mealid);                                  

    
    newFood.setName(name);
    newFood.setTypeby(typeby);
    newFood.setMealby(mealby);
    newFood.setCreatedby(createdby);
    
    return foodRepository.save(newFood);
    
    }
}