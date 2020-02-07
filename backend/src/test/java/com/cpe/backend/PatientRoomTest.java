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
public class PatientRoomTest {

    private Validator validator;
    @Autowired
    private PatientRoomRepository patientRoomRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6002060_testPatientRoomAllDataComplete() {

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setRoom_name("Emergency");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        Optional<PatientRoom> found = patientRoomRepository.findById(patientRoom.getRoom_id());
        assertEquals("Emergency", found.get().getRoom_name());
        
    }

    @Test
    void B6002060_testPatientRoomMustNotBeNull() {
        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setRoom_name(null);

        Set<ConstraintViolation<PatientRoom>> result = validator.validate(patientRoom);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PatientRoom> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("room_name", v.getPropertyPath().toString());
    }
}