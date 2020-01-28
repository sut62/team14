package com.cpe.backend;

import com.cpe.register.entity.*;
import com.cpe.personnel.entity.*;
import com.cpe.patientBed.entity.*;
import com.cpe.DiseasePackage.entity.*;
import com.cpe.register.repository.*;
import com.cpe.personnel.repository.*;
import com.cpe.patientBed.repository.*;
import com.cpe.DiseasePackage.repository.*;
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
    @Autowired
    private BloodtypeRepository  bloodtypeRepository;
    @Autowired
    private GenderRepository  genderRepository;
    @Autowired
    private PersonnelRepository  personnelRepository;
    @Autowired
    private EducationlevelRepository educationlevelRepository;
    @Autowired
    private MaritalstatusRepository maritalstatusRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PatientBedRepository patientBedRepository;
    @Autowired
    private PhysicalBedRepository physicalBedRepository;
    @Autowired
    private PatientZoneRepository patientZoneRepository;
    @Autowired
    private PatientRoomRepository patientRoomRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private LifeSpanRepository lifeSpanRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B6001902_testRegisterAllDataNotNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        register = registerRepository.saveAndFlush(register);

        final Optional<Register> found = registerRepository.findById(register.getId());
        assertEquals("Wattana", found.get().getAddname());
        assertEquals("Chaiyakun", found.get().getAddlastname());
        assertEquals("0902172445", found.get().getTelephone());
        assertEquals(personnel, found.get().getCreatedby());
        assertEquals(gendername, found.get().getGendername());
        assertEquals(bloodtypename, found.get().getBloodtypename());
        assertEquals(patientBed, found.get().getPatname());
        assertEquals(disease, found.get().getDesname());

        
        
    }
    @Test
    void B6001902_testNameMustNotBeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        final Register register = new Register();
        register.setAddname(null);
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("addname", v.getPropertyPath().toString());

    }
    @Test
    void B6001902_testLastnameMustNotBeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//

        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname(null);
        register.setTelephone("0902172445");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("addlastname", v.getPropertyPath().toString());

    }
    @Test
    void B6001902_testTelephoneMustNotBe11Digits() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//

                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("09021724455");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }
    @Test
    void B6001902_testTelephoneMustNotBe9Digits() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//   
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);

        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("090217244");
        register.setAddage(22);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }
    @Test
    void B6001902_testAgeNotMoreThanTo200Age() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);
        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(201);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("addage", v.getPropertyPath().toString());
    }
    void B6001902_testPersonnelComBoBoxMustNotBeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);
        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(201);
        register.setCreatedby(null);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("createdby", v.getPropertyPath().toString());
    }
    void B6001902_testGenderComBoBoxMustNotBeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);
        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(201);
        register.setCreatedby(personnel);
        register.setGendername(null);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("gendername", v.getPropertyPath().toString());
    }
    void B6001902_testBloodtypeComBoBoxMustNotBeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);
        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(201);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(null);
        register.setPatname(patientBed);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("bloodtypename", v.getPropertyPath().toString());
    }
    void B6001902_testPatientBedComBoBoxMustNotBeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);
        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(201);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(null);
        register.setDesname(disease);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("patientBed", v.getPropertyPath().toString());
    }
    void B6001902_testDiseaseComBoBoxMustNotBeNull() {
        //========================Personnel=================================//
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
        //====================================BedTest===============================
        PatientZone patientZone = new PatientZone();
        patientZone.setName("A1");
        patientZone = patientZoneRepository.saveAndFlush(patientZone);

        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setName("Normal Room");
        patientRoom = patientRoomRepository.saveAndFlush(patientRoom);

        PhysicalBed physicalBed = new PhysicalBed();
        physicalBed.setName("Aluminum");
        physicalBed = physicalBedRepository.saveAndFlush(physicalBed);

        //Use Data in subEntity to combobox
        PatientBed patientBed = new PatientBed();
        patientBed.setCreatedBy(personnel);//use personnel to set data hai combobox
        patientBed.setAtZone(patientZone);
        patientBed.setAtRoom(patientRoom);
        patientBed.setPhysicalBed(physicalBed);
        patientBed.setDetail("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //=========================== Disease================================//
        Type type = new Type();
        type.setType("ติดต่อ");
        type = typeRepository.saveAndFlush(type);

        LifeSpan lifespan = new LifeSpan();
        lifespan.setAge("วัยรุ่น");
        lifespan = lifeSpanRepository.saveAndFlush(lifespan);

        Disease disease = new Disease();
        disease.setName("Dengue Fever");
        disease.setSymptom("Sudden high fever Sudden high fever");
        disease.setType(type);
        disease.setLifespan(lifespan);
        disease.setPersonnel(personnel);
        disease = diseaseRepository.saveAndFlush(disease);
        //=======================Register====================================//
                
        Bloodtype bloodtypename = new Bloodtype();
        bloodtypename.setBlood_name("A");
        bloodtypename = bloodtypeRepository.saveAndFlush(bloodtypename);

        Gender gendername = new Gender();
        gendername.setGen_name("Man");
        gendername = genderRepository.saveAndFlush(gendername);
        final Register register = new Register();
        register.setAddname("Wattana");
        register.setAddlastname("Chaiyakun");
        register.setTelephone("0902172445");
        register.setAddage(201);
        register.setCreatedby(personnel);
        register.setGendername(gendername);
        register.setBloodtypename(bloodtypename);
        register.setPatname(patientBed);
        register.setDesname(null);
        final Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("disease", v.getPropertyPath().toString());
    }
}


















