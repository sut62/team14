package com.cpe.backend;

import com.cpe.register.entity.*;
import com.cpe.register.repository.*;
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
public class GenderTests {

    private Validator validator;
    @Autowired
    private GenderRepository genderRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6024321_testGenderAllDataComplete() {

        Gender gender = new Gender();
        gender.setGen_name("Man");
        gender = genderRepository.saveAndFlush(gender);

        Optional<Gender> found = genderRepository.findById(gender.getId());
        assertEquals("Man", found.get().getGen_name());
        
    }

    @Test
    void B6024321_testGenderMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGen_name(null);

        Set<ConstraintViolation<Gender>> result = validator.validate(gender);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Gender> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gen_name", v.getPropertyPath().toString());
    }
}