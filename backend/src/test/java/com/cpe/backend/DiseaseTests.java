package com.cpe.backend;

import com.cpe.DiseasePackage.entity.*;
import com.cpe.DiseasePackage.repository.*;
import com.cpe.personnel.entity.*;
import com.cpe.personnel.repository.*;

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
import java.time.LocalDate;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class DiseaseTests {

    private Validator validator;

    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private LifeSpanRepository lifeSpanRepository;
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
    @Test
    void b6001414_testDiseaseAllDataNotNull() {
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);
        
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

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);

        Optional<Disease> found = diseaseRepository.findById(disease.getId());
        assertEquals("Dengue Fever", found.get().getName());
        assertEquals("Sudden high fever Sudden high fever", found.get().getSymptom());
        assertEquals(type, found.get().getType());
        assertEquals(lifespan, found.get().getLifespan());
        assertEquals(personnel, found.get().getPersonnel());


    }
    @Test
    void b6001414_testNameMustNotNull() {
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);
        
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

        Disease disease = new Disease();
        disease.setName(null);
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

     @Test
    void b6001414_testSymptomMustNotNull() {
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);
        
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

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom(null);
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

     @Test
    void b6001414_testTypeNotBeNull() {
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);
        
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

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(null);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }
    @Test
    void b6001414_testLifespanNotBeNull() {
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);
        
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

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(null);
        disease.setPersonnel(personnel);
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("lifespan", v.getPropertyPath().toString());
    }

    @Test
    void b6001414_testPersonnelNotBeNull() {
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);
        
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

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(null);
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("personnel", v.getPropertyPath().toString());
    }
}