package com.cpe.backend;

import com.cpe.DiseasePackage.entity.Disease;
import com.cpe.DiseasePackage.repository.DiseaseRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class DiseaseTests {

    private Validator validator;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6001414_testDiseaseAllDataNotNull() {
        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease = diseaseRepository.saveAndFlush(disease);

        Optional<Disease> found = diseaseRepository.findById(disease.getId());
        assertEquals("Dengue Fever", found.get().getName());
        assertEquals("Sudden high fever Sudden high fever", found.get().getSymptom());
    }
    @Test
    void b6001414_testNameMustNotNull() {
        Disease disease = new Disease();
        disease.setName(null);
        disease.setSymptom("Sudden high fever Sudden high fever");
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

     @Test
    void b6001414_testSymptomMustNotNull() {
        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom(null);
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }
     @Test
    void b6001414_testSymptomMustNot71Char() {
        Disease disease = new Disease();
        disease.setName("0123456789012345678901234567890123456789012345678901234567890123456789a");
        disease.setSymptom("Sudden high fever Sudden high fever");
        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        assertEquals(1, result.size());

        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("size must be between 1 and 70", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}