package com.cpe.backend;


import com.cpe.patientBed.entity.*;
import com.cpe.patientBed.repository.*;
import com.cpe.personnel.entity.*;
import com.cpe.personnel.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import com.fasterxml.jackson.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BedTest {

    private Validator validator;

    @Autowired
    private PatientBedRepository patientBedRepository;
    @Autowired
    private PhysicalBedRepository physicalBedRepository;
    @Autowired
    private PatientZoneRepository patientZoneRepository;
    @Autowired
    private PatientRoomRepository patientRoomRepository;

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
    @Test
    void b6002060_bedTestOKWithAllData() {
        
        //====================================Personnel===============================
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

        //====================================Me===============================
        //set Data for subEntity
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
        patientBed.setDetail("ขาเตียงด้านซ้ายล่างมีรอยถลอก AZaz 123");
        patientBed.setShow("test");
        patientBed = patientBedRepository.saveAndFlush(patientBed);

        //all data tee tong chai mun tong chai kormul krop tung ja test case nee dai
        Optional<PatientBed> found = patientBedRepository.findById(patientBed.getPatientBed_id());
        assertEquals(personnel, found.get().getCreatedBy());//personnel is kong nai pon tee save pai law mee data tarang yoi tangmod kong nai pon||CreatedBy is id kong me tee bog garn link  data naipon
        assertEquals(patientZone, found.get().getAtZone());//use relation for link get  getAtZone in major Entity for link to sub entity
        assertEquals(patientRoom, found.get().getAtRoom());
        assertEquals(physicalBed, found.get().getPhysicalBed());
        assertEquals("ขาเตียงด้านซ้ายล่างมีรอยถลอก AZaz 123", found.get().getDetail());
        assertEquals("test", found.get().getShow());
        
    }
//**********************************************************Test Personnel Notnull**********************************************************
    @Test
    void b6002060_Personnel_Combobox_MustNotBeNull(){
          //====================================Personnel===============================
          //mai tong set kong nai pon ja dai set null for test null
  
          //====================================Me===============================
          //set Data for subEntity
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
          patientBed.setCreatedBy(null);//use personnel to set data hai combobox
          patientBed.setAtZone(patientZone);
          patientBed.setAtRoom(patientRoom);
          patientBed.setPhysicalBed(physicalBed);
          patientBed.setDetail("**ขาเตียงด้านซ้ายล่างมีรอยถลอก AZaz 123");
          patientBed.setShow("test");
          //not use saveandflush  cause use just column maidai chai tung class
  
          //check all error by validator ---> patientBed to assign in result
          Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);
  
          //error just only one cause we to do one error result.size=number of error to compare
          assertEquals(1, result.size());
  
          //change result to ConstraintViolation cause get message error
          ConstraintViolation<PatientBed> v = result.iterator().next();
          assertEquals("must not be null", v.getMessage());
          assertEquals("createdBy", v.getPropertyPath().toString());


    }


//*************************************************Test Zone Notnull*****************************************************
    @Test
    void b6002060_patientZone_Combobox_MustNotBeNull(){
        //====================================Personnel===============================
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
  
          //====================================Me===============================
          //set Data for subEntity
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
          patientBed.setAtZone(null);
          patientBed.setAtRoom(patientRoom);
          patientBed.setPhysicalBed(physicalBed);
          patientBed.setDetail("**ขาเตียงด้านซ้ายล่างมีรอยถลอก AZaz 123");
          patientBed.setShow("test");
          //not use saveandflush  cause use just column maidai chai tung class
  
          //check all error by validator ---> patientBed to assign in result
          Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);
  
          //error just only one cause we to do one error result.size=number of error to compare
          assertEquals(1, result.size());
  
          //change result to ConstraintViolation cause get message error
          ConstraintViolation<PatientBed> v = result.iterator().next();
          assertEquals("must not be null", v.getMessage());
          assertEquals("atZone", v.getPropertyPath().toString());


    }



//**********************************************************Test Room Notnull**********************************************************
    @Test
    void b6002060_patientRoom_Combobox_MustNotBeNull(){
        //====================================Personnel===============================
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
  
          //====================================Me===============================
          //set Data for subEntity
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
          patientBed.setAtRoom(null);
          patientBed.setPhysicalBed(physicalBed);
          patientBed.setDetail("**ขาเตียงด้านซ้ายล่างมีรอยถลอก AZaz 123");
          patientBed.setShow("test");
          //not use saveandflush  cause use just column maidai chai tung class
  
          //check all error by validator ---> patientBed to assign in result
          Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);
  
          //error just only one cause we to do one error result.size=number of error to compare
          assertEquals(1, result.size());
  
          //change result to ConstraintViolation cause get message error
          ConstraintViolation<PatientBed> v = result.iterator().next();
          assertEquals("must not be null", v.getMessage());
          assertEquals("atRoom", v.getPropertyPath().toString());
    }


 //**********************************************************Test Physical Notnull**********************************************************
 @Test
 void b6002060_PhysicalBed_Combobox_MustNotBeNull(){
     //====================================Personnel===============================
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

       //====================================Me===============================
       //set Data for subEntity
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
       patientBed.setPhysicalBed(null);
       patientBed.setDetail("**ขาเตียงด้านซ้ายล่างมีรอยถลอก AZaz 123");
       patientBed.setShow("test");
       //not use saveandflush  cause use just column maidai chai tung class

       //check all error by validator ---> patientBed to assign in result
       Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

       //error just only one cause we to do one error result.size=number of error to compare
       assertEquals(1, result.size());

       //change result to ConstraintViolation cause get message error
       ConstraintViolation<PatientBed> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("physicalBed", v.getPropertyPath().toString());
 }


   @Test
    void b6002060_testDetailNotbeNull() {
        //====================================Personnel===============================
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

        //====================================Me===============================
        //set Data for subEntity
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
        patientBed.setDetail(null);
        patientBed.setShow("test");
        //not use saveandflush  cause use just column maidai chai tung class

        //check all error by validator ---> patientBed to assign in result
        Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

        //error just only one cause we to do one error result.size=number of error to compare
        assertEquals(1, result.size());

        //change result to ConstraintViolation cause get message error
        ConstraintViolation<PatientBed> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("detail", v.getPropertyPath().toString());
    }
    @Test
    void b6002060_testDetailOKWithNotbe201Char() {
        //====================================Personnel===============================
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

        //====================================Me===============================
        //set Data for subEntity
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
        patientBed.setDetail("ก01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        patientBed.setShow("test");

        //find number of error
        Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

        //compare is only one error
        assertEquals(1, result.size());

        //translate result to get message error
        ConstraintViolation<PatientBed> v = result.iterator().next();
        assertEquals("size must be between 1 and 200", v.getMessage());
        assertEquals("detail", v.getPropertyPath().toString());
    }
//****************************************************************************Pattern  detail************************************************************************* */

@Test
void b6002060_testPatternDetailOkWithNotSymbol() {
    //====================================Personnel===============================
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

    //====================================Me===============================
    //set Data for subEntity
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
    patientBed.setDetail("**ขาเตียงด้านซ้ายล่างมีรอยถลอก  AZaz 123.");
    patientBed.setShow("test");

    //find number of error
    Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

    //compare is only one error
    assertEquals(1, result.size());

    //translate result to get message error
    ConstraintViolation<PatientBed> v = result.iterator().next();
    assertEquals("must match \"[[ก-๙][ ]A-Za-z0-9[+][-][*]]*\"", v.getMessage());
    assertEquals("detail", v.getPropertyPath().toString());
}
     @Test
    void b6002060_testShowMustNotBeNull(){
         //====================================Personnel===============================
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
 
         //====================================Me===============================
         //set Data for subEntity
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
         patientBed.setDetail("**ขาเตียงด้านซ้ายล่างมีรอยถลอก  AZaz 123");
         patientBed.setShow(null);

        Set<ConstraintViolation<PatientBed>> result = validator.validate(patientBed);

        assertEquals(1, result.size());

        ConstraintViolation<PatientBed> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("show", v.getPropertyPath().toString());
    }
}




















