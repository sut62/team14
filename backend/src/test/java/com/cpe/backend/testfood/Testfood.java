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
public class Testfood {

    private Validator validator;

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodtypeRepository foodtypeRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private EducationlevelRepository educationlevelRepository;
    @Autowired
    private MaritalstatusRepository maritalstatusRepository;
    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
     @Test
    void B6021726_testFoodAllDataComplete() {

        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);

        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);        

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);
        personnel = personnelRepository.saveAndFlush(personnel);


        Food name = new Food();
        name.setName("ข้าวต้ม");
        name.setCreatedby(personnel);
        name.setTypeby(type);
        name.setMealby(meal);
        name = foodRepository.saveAndFlush(name);

        // error message ตรงชนิด และถูก field
        Optional<Food> found = foodRepository.findById(name.getId());
        assertEquals("ข้าวต้ม", found.get().getName());
        assertEquals(type, found.get().getTypeby());
        assertEquals(meal, found.get().getMealby());

        assertEquals(personnel, found.get().getCreatedby());
    }


    @Test
    void b6021726_testFoodOKWithNotbeNull() {

        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);

        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);        

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);
        personnel = personnelRepository.saveAndFlush(personnel);
        

        Food name = new Food();
        name.setName(null);
        name.setCreatedby(personnel);
        name.setTypeby(type);
        name.setMealby(meal);

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
    @Test
    void B6021726_testSize3() {

        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);

        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);        

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);
        personnel = personnelRepository.saveAndFlush(personnel);


        Food name= new Food();
        name.setName("กกก");
        name.setCreatedby(personnel);
        name.setTypeby(type);
        name.setMealby(meal);

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());
        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("size must be between 4 and 20", v.getMessage());
        assertEquals("name",v.getPropertyPath().toString());
    }
    @Test
    void B6021726_testSize21() {

        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);

        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);        

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);
        personnel = personnelRepository.saveAndFlush(personnel);


        Food name = new Food();
        name.setName("กกกกกกกกกกกกกกกกกกกกกก");
        name.setCreatedby(personnel);
        name.setTypeby(type);
        name.setMealby(meal);

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("size must be between 4 and 20", v.getMessage());
        assertEquals("name",v.getPropertyPath().toString());
    }
    @Test
    void B6021726_testFoodMustNotBeDigi() {

        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);

        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);        

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);
        personnel = personnelRepository.saveAndFlush(personnel);


        Food name = new Food();
        name.setName("ข้าวต้ม1");
        name.setCreatedby(personnel);
        name.setTypeby(type);
        name.setMealby(meal);
        
        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("must match \"[^0-9]*\"", v.getMessage());
        assertEquals("name",v.getPropertyPath().toString());
    }
     @Test
    void B6021726_Personnel_Combobox_MustNotBeNull(){

        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food name = new Food();
        name.setName("ข้าวต้ม");
        name.setCreatedby(null);
        name.setTypeby(type);
        name.setMealby(meal);

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createdby", v.getPropertyPath().toString());

    }
    @Test
    void B6021726_Foodtype_Combobox_MustNotBenull(){
        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);

        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);        

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);
        personnel = personnelRepository.saveAndFlush(personnel);
        

        Food name = new Food();
        name.setName("ข้าวต้ม");
        name.setCreatedby(personnel);
        name.setTypeby(null);
        name.setMealby(meal);

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("typeby", v.getPropertyPath().toString());
    }
        @Test
    void B6021726_Meal_Combobox_MustNotBenull(){
        Foodtype type = new Foodtype();
        type.setType("อาหารธรรมดา");
        type = foodtypeRepository.saveAndFlush(type);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);

        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);        

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);
        personnel = personnelRepository.saveAndFlush(personnel);
        

        Food name = new Food();
        name.setName("ข้าวต้ม");
        name.setCreatedby(personnel);
        name.setTypeby(type);
        name.setMealby(null);

        Set<ConstraintViolation<Food>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<Food> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("mealby", v.getPropertyPath().toString());
    }

}


















