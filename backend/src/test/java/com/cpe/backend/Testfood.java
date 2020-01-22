package com.cpe.backend;

import com.cpe.backendfood.entity.Food;
import com.cpe.backendfood.repository.FoodRepository;


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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.constraints.NotBlank;



@DataJpaTest
public class Testfood {

    private Validator validator;

    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
     @Test
    void B6021726_testFoodOKWithAllDataNotNull() {
        Food name = new Food();
        name.setName("ข้าวต้ม");
        name = foodRepository.saveAndFlush(name);

        // error message ตรงชนิด และถูก field
        Optional<Food> found = foodRepository.findById(name.getId());
        assertEquals("ข้าวต้ม", found.get().getName());
    }



    @Test
    void B6021726_testSize3() {
        Food name= new Food();
        name.setName("123");

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());
        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("size must be between 4 and 20", v.getMessage());
        assertEquals("name",v.getPropertyPath().toString());
    }
    @Test
    void B6021726_testSize21() {
        Food name= new Food();
        name.setName("123456789012345678901");

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("size must be between 4 and 20", v.getMessage());
        assertEquals("name",v.getPropertyPath().toString());
    }
    @Test
    void b6021726_testFoodOKWithNotbeNull() {
        Food name = new Food();
        name.setName(null);

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

}


















