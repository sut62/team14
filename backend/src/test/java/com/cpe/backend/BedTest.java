package com.cpe.backend;

import com.cpe.patientBed.entity.PatientBed;
import com.cpe.patientBed.repository.PatientBedRepository;
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
public class BedTest {

    private Validator validator;

    @Autowired
    private PatientBedRepository patientBedRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6002060_bedTestOKWithAllDataNotNull() {
        PatientBed patientBed = new PatientBed();
        patientBed.setDetail("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        Optional<PatientBed> found = patientBedRepository.findById(patientBed.getPatientBed_id());
        assertEquals("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", found.get().getDetail());
        assertEquals("test", found.get().getShow());
        
    }
   @Test
    void b6002060_testDetailNotbeNull() {
        PatientBed patientBed = new PatientBed();
        patientBed.setDetail(null);
        patientBed.setShow("test");
        Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

        assertEquals(1, result.size());

        ConstraintViolation<PatientBed> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("detail", v.getPropertyPath().toString());
    }
    @Test
    void b6002060_testDetailOKWithNotbe101Char() {
        PatientBed patientBed = new PatientBed();
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        patientBed.setShow("test");
        Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

        assertEquals(1, result.size());

        ConstraintViolation<PatientBed> v = result.iterator().next();
        assertEquals("size must be between 1 and 100", v.getMessage());
        assertEquals("detail", v.getPropertyPath().toString());
    }
     @Test
    void b6002060_testShowMustNotBeNull(){
        PatientBed patientBed = new PatientBed();
        patientBed.setDetail("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow(null);
        Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

        assertEquals(1, result.size());

        ConstraintViolation<PatientBed> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("show", v.getPropertyPath().toString());
    }
}




















