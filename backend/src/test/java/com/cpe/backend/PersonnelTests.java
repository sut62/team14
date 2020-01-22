package com.cpe.backend;

import com.cpe.personnel.entity.Personnel;
import com.cpe.personnel.repository.PersonnelRepository;
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
public class PersonnelTests {

    private Validator validator;

    @Autowired
    private PersonnelRepository personnelRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6024321_testPersonnelAllDataNotNull() {
        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel = personnelRepository.saveAndFlush(personnel);

        Optional<Personnel> found = personnelRepository.findById(personnel.getId());
        assertEquals("sopon", found.get().getFirstname());
        assertEquals("phudee", found.get().getLastname());
        assertEquals("0856845611", found.get().getTelephone());
        assertEquals("12345648790", found.get().getPassword());
        
    }
    @Test
    void B6024321_testLastnameMustNotBeNull() {
        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname(null);
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("lastname", v.getPropertyPath().toString());
    }
    @Test
    void B6024321_testPersonnelIdMustNotBe11Digits() {
        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("08568456111");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }
    @Test
    void B6024321_testTelephoneMustNotBe9Digits() {
        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("085684561");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }
    @Test
    void B6024321_testLocalTimeDateMustNotBePresent(){
        Personnel personnel = new Personnel();
        LocalDate date = LocalDate.now();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(date);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        //must has 1 error
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<Personnel> vi = result.iterator().next();
        assertEquals("must be a past date", vi.getMessage());
        assertEquals("Birthday", vi.getPropertyPath().toString());
    }
}


















