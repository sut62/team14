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
public class BloodtypeTests {

    private Validator validator;
    @Autowired
    private BloodtypeRepository bloodtypeRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6024321_testBloodtypeAllDataComplete() {

        Bloodtype bloodtype = new Bloodtype();
        bloodtype.setBlood_name("B");
        bloodtype = bloodtypeRepository.saveAndFlush(bloodtype);

        Optional<Bloodtype> found = bloodtypeRepository.findById(bloodtype.getId());
        assertEquals("B", found.get().getBlood_name());
        
    }

    @Test
    void B6024321_testBloodtypeMustNotBeNull() {
        Bloodtype bloodtype = new Bloodtype();
        bloodtype.setBlood_name(null);

        Set<ConstraintViolation<Bloodtype>> result = validator.validate(bloodtype);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Bloodtype> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("blood_name", v.getPropertyPath().toString());
    }
}