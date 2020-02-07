package com.cpe.backend;

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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class EducationlevelTest {

    private Validator validator;
    @Autowired
    private EducationlevelRepository educationlevelRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6024321_testEducationlevelAllDataComplete() {

        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Optional<Educationlevel> found = educationlevelRepository.findById(level.getId());
        assertEquals("Bachelor of Arts", found.get().getEducationlevel_name());
        
    }

    @Test
    void B6024321_testEducationlevel_nameMustNotBeNull() {
        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name(null);

        Set<ConstraintViolation<Educationlevel>> result = validator.validate(level);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Educationlevel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("educationlevel_name", v.getPropertyPath().toString());
    }
}
