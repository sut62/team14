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
public class PatientZoneTest {

    private Validator validator;
    @Autowired
    private PatientZoneRepository patientZoneRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6002060_testPatientZoneAllDataComplete() {

        PatientZone patientZone = new PatientZone();
        patientZone.setZone_name("A5");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        Optional<PatientZone> found = patientZoneRepository.findById(patientZone.getZone_id());
        assertEquals("A5", found.get().getZone_name());
        
    }

    @Test
    void B6002060_testPatientZoneMustNotBeNull() {
        PatientZone patientZone = new PatientZone();
        patientZone.setZone_name(null);

        Set<ConstraintViolation<PatientZone>> result = validator.validate(patientZone);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PatientZone> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("zone_name", v.getPropertyPath().toString());
    }
}