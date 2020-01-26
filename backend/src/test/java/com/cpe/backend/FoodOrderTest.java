package com.cpe.backend;

import com.cpe.register.entity.*;
import com.cpe.personnel.entity.*;
import com.cpe.patientBed.entity.*;
import com.cpe.DiseasePackage.entity.*;
import com.cpe.register.repository.*;
import com.cpe.personnel.repository.*;
import com.cpe.patientBed.repository.*;
import com.cpe.DiseasePackage.repository.*;
import com.cpe.backendfood.entity.*;
import com.cpe.backendfood.repository.*;

import com.cpe.orderfood.entity.FoodOrder;
import com.cpe.orderfood.repository.FoodOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import com.fasterxml.jackson.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class FoodOrderTest {

    private Validator validator;

    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private BloodtypeRepository  bloodtypeRepository;
    @Autowired
    private GenderRepository  genderRepository;
    @Autowired
    private PersonnelRepository  personnelRepository;
    @Autowired
    private EducationlevelRepository educationlevelRepository;
    @Autowired
    private MaritalstatusRepository maritalstatusRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PatientBedRepository patientBedRepository;
    @Autowired
    private PhysicalBedRepository physicalBedRepository;
    @Autowired
    private PatientZoneRepository patientZoneRepository;
    @Autowired
    private PatientRoomRepository patientRoomRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private LifeSpanRepository lifeSpanRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodtypeRepository foodtypeRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private FoodOrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6026042_testFoodorderOKWithAllDataNotNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        //FoodOrder
        Date now = new Date();
        FoodOrder order = new FoodOrder();
        order.setDetails("กขคabc+-*9012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        order.setOrderDate(now);
        order.setFood(food);
        order.setPatient(register);
        order.setOrderBy(personnel);
        order = orderRepository.saveAndFlush(order);

        Optional<FoodOrder> found = orderRepository.findById(order.getId());
        assertEquals("กขคabc+-*9012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", found.get().getDetails());
        assertEquals(now, found.get().getOrderDate());
        assertEquals(food, found.get().getFood());
        assertEquals(register, found.get().getPatient());
        assertEquals(personnel, found.get().getOrderBy());

    }
    @Test
    void b6026042_testDetailsOKWithNotbeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        //OrderFood

        FoodOrder order = new FoodOrder();
        order.setDetails(null);
        order.setOrderDate(new Date());
        order.setFood(food);
        order.setPatient(register);
        order.setOrderBy(personnel);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("details", v.getPropertyPath().toString());
    }
    @Test
    void b6026042_testDetailsOKWithNotbe101Char() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        //OrderFood

        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789a");
        order.setOrderDate(new Date());
        order.setFood(food);
        order.setPatient(register);
        order.setOrderBy(personnel);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("size must be between 1 and 100", v.getMessage());
        assertEquals("details", v.getPropertyPath().toString());
    }
    @Test
    void b6026042_testDetailsOKWithNotSymbol() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        //OrderFood

        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456!%+");
        order.setOrderDate(new Date());
        order.setFood(food);
        order.setPatient(register);
        order.setOrderBy(personnel);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must match \"[ก-ฮA-Za-z0-9[+][-][*]]*\"", v.getMessage());
        assertEquals("details", v.getPropertyPath().toString());
    }
    @Test
    void b6026042_testOrderDateMustNotBeNull(){
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        order.setOrderDate(null);
        order.setFood(food);
        order.setPatient(register);
        order.setOrderBy(personnel);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("orderDate", v.getPropertyPath().toString());
    }

    @Test
    void b6026042_testFoodMustNotBeNull(){
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        order.setOrderDate(new Date());
        order.setFood(null);
        order.setPatient(register);
        order.setOrderBy(personnel);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("food", v.getPropertyPath().toString());
    }
    @Test
    void b6026042_testOrderByMustNotBeNull(){
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        order.setOrderDate(new Date());
        order.setFood(food);
        order.setPatient(register);
        order.setOrderBy(null);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("orderBy", v.getPropertyPath().toString());
    }
    @Test
    void b6026042_testPatientMustNotBeNull(){
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);
        //Food
        Foodtype ftype = new Foodtype();
        ftype.setType("อาหารธรรมดา");
        ftype = foodtypeRepository.saveAndFlush(ftype);

        Meal meal = new Meal();
        meal.setMealtype("เช้า");
        meal = mealRepository.saveAndFlush(meal);

        Food food = new Food();
        food.setName("ข้าวต้ม");
        food.setCreatedby(personnel);
        food.setTypeby(ftype);
        food.setMealby(meal);
        food = foodRepository.saveAndFlush(food);

        FoodOrder order = new FoodOrder();
        order.setDetails("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        order.setOrderDate(new Date());
        order.setFood(food);
        order.setPatient(null);
        order.setOrderBy(personnel);
        Set<ConstraintViolation<FoodOrder>> result = validator.validate(order);

        assertEquals(1, result.size());

        ConstraintViolation<FoodOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("patient", v.getPropertyPath().toString());
    }

}