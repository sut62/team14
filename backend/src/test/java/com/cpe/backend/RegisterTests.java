package com.cpe.backend;

import com.cpe.register.entity.Register;
import com.cpe.register.repository.RegisterRepository;
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
public class RegisterTests {

    private Validator validator;

    @Autowired
    private RegisterRepository registerRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6001902_testRegisterAllDataNotNull() {
        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register = registerRepository.saveAndFlush(register);

        Optional<Register> found = registerRepository.findById(register.getId());
        assertEquals("Wattana", found.get().getAddname());
        assertEquals("Chaiyakun", found.get().getAddlastname());
        assertEquals("0902172445", found.get().getTelephone());
        
    }
    @Test
    void B6001902_testNameAndLastnameMustNotBeNull() {
        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname(null);
        register.setTelephone("0902172445");
        register.setAddage(22);
        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("addlastname", v.getPropertyPath().toString());

    }
    @Test
    void B6001902_testTelephoneMustNotBe11Digits() {
        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("09021724455");
        register.setAddage(22);

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }
    @Test
    void B6024321_testTelephoneMustNotBe9Digits() {
        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("090217244");
        register.setAddage(22);

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }
    @Test
    void B6001902_testAgeNotMoreThanTo200Age() { // ใส่ข้อมูลปกติ
        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(201);

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("addage", v.getPropertyPath().toString());
    }
}


















