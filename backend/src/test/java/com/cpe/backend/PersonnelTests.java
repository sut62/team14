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
public class PersonnelTests {

    private Validator validator;

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

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6024321_testPersonnelAllDataComplete() {

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

        Optional<Personnel> found = personnelRepository.findById(personnel.getId());
        assertEquals("sopon", found.get().getFirstname());
        assertEquals("phudee", found.get().getLastname());
        assertEquals("0856845611", found.get().getTelephone());
        assertEquals("12345648790", found.get().getPassword());
        assertEquals(LocalDate.parse("1999-01-29"), found.get().getBirthday());
        assertEquals(status, found.get().getStatus());
        assertEquals(posit, found.get().getPosit());
        assertEquals(level, found.get().getLevel());
        
    }

    @Test
    void B6024321_testFirstnameMustNotBeNull() {
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
        personnel.setFirstname(null);
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("firstname", v.getPropertyPath().toString());
    }

    
    @Test
    void B6024321_testFirstnameWithNotbe101Char() {
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
        personnel.setFirstname("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("size must be between 2 and 100", v.getMessage());
        assertEquals("firstname", v.getPropertyPath().toString());
    }

    @Test
    void B6024321_testFirstnameMustNotDigits() {
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
        personnel.setFirstname("sopon1");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z[ก-ฮ]*]*\"", v.getMessage());
        assertEquals("firstname", v.getPropertyPath().toString());
    }

    @Test
    void B6024321_testLastnameMustNotBeNull() {
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
        personnel.setLastname(null);
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("lastname", v.getPropertyPath().toString());
    }

    @Test
    void B6024321_testLastnameWithNotbe101Char() {
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
        personnel.setLastname("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("size must be between 2 and 100", v.getMessage());
        assertEquals("lastname", v.getPropertyPath().toString());
    }

    
    @Test
    void B6024321_testLastnameMustNotDigits() {
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
        personnel.setLastname("phudee2");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z[ก-ฮ]*]*\"", v.getMessage());
        assertEquals("lastname", v.getPropertyPath().toString());
    }

    @Test
    void B6024321_testTelephoneMustNotBeNull() {
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
        personnel.setTelephone(null);
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B6024321_testTelephoneMustNotChar() {
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
        personnel.setTelephone("085684561a");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B6024321_testTelephoneMustNotBe11Digits() {
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
        personnel.setTelephone("08568456111");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

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
        personnel.setTelephone("085684561");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B6024321_testLocalDateMustNotBeNull() {
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
        personnel.setBirthday(null);
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Birthday", v.getPropertyPath().toString());
    }


    @Test
    void B6024321_testLocalDateMustNotBePresent(){
        
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
        LocalDate date = LocalDate.now();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(date);
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        //must has 1 error
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<Personnel> vi = result.iterator().next();
        assertEquals("must be a past date", vi.getMessage());
        assertEquals("Birthday", vi.getPropertyPath().toString());
    }
    @Test
    void B6024321_testLocalDateMustNotBefuture(){
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
        personnel.setBirthday(LocalDate.parse("2592-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        //must has 1 error
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<Personnel> vi = result.iterator().next();
        assertEquals("must be a past date", vi.getMessage());
        assertEquals("Birthday", vi.getPropertyPath().toString());
    }

    @Test
    void B6024321_testPasswordMustNotBeNull() {
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
        personnel.setPassword(null);
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }
    
    @Test
    void B6024321_testPasswordWithNotbe101Char() {
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
        personnel.setPassword("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss0");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("size must be between 2 and 100", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    

    @Test
    void B6024321_testEducationlevelMustNotBeNull() { 
        
        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);
        
        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);

        
        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(posit);
        personnel.setLevel(null);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("level", v.getPropertyPath().toString());
    }
    @Test
    void B6024321_testMaritalstatusMustNotBeNull() { 
        
        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);
        
        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);

        
        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(null);
        personnel.setPosit(posit);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());
    }
    @Test
    void B6024321_testPositionMustNotBeNull() { 
        
        Maritalstatus status = new Maritalstatus();
        status.setMaritalstatus_name("single");
        status = maritalstatusRepository.saveAndFlush(status);
        
        Position posit = new Position();
        posit.setPosition_name("Doctor");
        posit = positionRepository.saveAndFlush(posit);

        
        Educationlevel level = new Educationlevel();
        level.setEducationlevel_name("Bachelor of Arts");
        level = educationlevelRepository.saveAndFlush(level);

        Personnel personnel = new Personnel();
        personnel.setFirstname("sopon");
        personnel.setLastname("phudee");
        personnel.setTelephone("0856845611");
        personnel.setPassword("12345648790");
        personnel.setBirthday(LocalDate.parse("1999-01-29"));
        personnel.setStatus(status);
        personnel.setPosit(null);
        personnel.setLevel(level);

        Set<ConstraintViolation<Personnel>> result = validator.validate(personnel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Personnel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("posit", v.getPropertyPath().toString());
    }
}


















