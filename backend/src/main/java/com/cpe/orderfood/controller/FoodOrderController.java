package com.cpe.orderfood.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.util.Date;
import com.cpe.orderfood.entity.FoodOrder;
import com.cpe.personnel.entity.Personnel;
import com.cpe.backendfood.entity.Food;
import com.cpe.register.entity.Register;

import com.cpe.orderfood.repository.FoodOrderRepository;
import com.cpe.personnel.repository.PersonnelRepository;
import com.cpe.backendfood.repository.FoodRepository;
import com.cpe.register.repository.RegisterRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.200:8081")
@RestController
public class FoodOrderController {
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private final FoodOrderRepository foodOrderRepository;

    FoodOrderController(FoodOrderRepository foodOrderRepository) {
        this.foodOrderRepository = foodOrderRepository;
    }

    @GetMapping("/foodorder")
    public Collection<FoodOrder> FoodOrder() {
        return foodOrderRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/foodorder/{food_id}/{personnel_id}/{register_id}/{details}")
    public FoodOrder newFoodOrder(FoodOrder newFoodOrder,
                                  @PathVariable long food_id,
                                  @PathVariable long personnel_id,
                                  @PathVariable long register_id,
                                  @PathVariable String  details){

        Food order = foodRepository.findById(food_id);
        Personnel employee = personnelRepository.findById(personnel_id);
        Register patient = registerRepository.findById(register_id);

        newFoodOrder.setFood(order);
        newFoodOrder.setOrderBy(employee);
        newFoodOrder.setPatient(patient);
        newFoodOrder.setDetails(details);
        newFoodOrder.setOrderDate(new Date());
        return foodOrderRepository.save(newFoodOrder);

    }
}
