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
public class Testfoodtype {

    private Validator validator;
    @Autowired
    private FoodtypeRepository foodtypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6021726_FoodtypeAllDataComplete() {

        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Optional<Foodtype> found = foodtypeRepository.findById(type.getId());
        assertEquals("อาหารธรรมดา", found.get().getType());
        
    }

    @Test
    void B6021726_FoodtypeMustNotBeNull() {
        Foodtype type = new Foodtype();
        type.setType(null);

        Set<ConstraintViolation<Foodtype>> result = validator.validate(type);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Foodtype> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }
}