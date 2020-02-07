package com.cpe.backend;

import com.cpe.patientBed.entity.*;
import com.cpe.patientBed.repository.*;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class PhysicalBedTest {

    private Validator validator;
    @Autowired
    private PhysicalBedRepository physicalBedRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6002060_testPhysicalBedAllDataComplete() {

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setPhysBed_name("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        Optional<PhysicalBed> found = physicalBedRepository.findById(physicalBed.getPhysBed_id());
        assertEquals("Aluminum", found.get().getPhysBed_name());
        
    }
    @Test
    void B6002060_testPhysicalBedMustNotBeNull() {
        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setPhysBed_name(null);

        Set<ConstraintViolation<PhysicalBed>> result = validator.validate(physicalBed);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PhysicalBed> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("physBed_name", v.getPropertyPath().toString());
    }
}