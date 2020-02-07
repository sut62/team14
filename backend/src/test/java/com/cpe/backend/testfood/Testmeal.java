package com.cpe.backend.testfood;

import com.cpe.backendfood.entity.*;
import com.cpe.backendfood.repository.*;
import com.cpe.personnel.entity.*;
import com.cpe.personnel.repository.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.constraints.NotBlank;

@DataJpaTest
public class Testmeal {

    private Validator validator;
    @Autowired
    private MealRepository mealRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6021726_MealAllDataComplete() {

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Optional<Meal> found = mealRepository.findById(meal.getId());
        assertEquals("เช้า", found.get().getMealtype());
        
    }

    @Test
    void B6021726_MealMustNotBeNull() {
        Meal meal = new Meal();
        meal.setMealtype(null);

        Set<ConstraintViolation<Meal>> result = validator.validate(meal);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Meal> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("mealtype", v.getPropertyPath().toString());
    }
}