package com.cpe;

import com.cpe.DiseasePackage.entity.*;
import com.cpe.DiseasePackage.repository.*;
import com.cpe.patientBed.entity.*;
import com.cpe.patientBed.repository.*;
import com.cpe.personnel.entity.*;
import com.cpe.personnel.repository.*;
import com.cpe.register.entity.*;
import com.cpe.register.repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	
    ApplicationRunner init(TypeRepository typeRepository,LifeSpanRepository lifespanRepository 
	,PatientRoomRepository patientRoomRepository,PatientZoneRepository patientZoneRepository, PhysicalBedRepository physicalBedRepository,
	PositionRepository positionRepository, MaritalstatusRepository maritalstatusRepository, EducationlevelRepository educationlevelRepository,GenderRepository genderRepository,BloodtypeRepository bloodtypeRepository) {
		return args -> {

			Stream.of("วัยทารก", "วัยเด็ก", "วัยรุ่น", "วัยผู้ใหญ่" ,"วัยชรา").forEach(age -> {
                LifeSpan lifespan = new LifeSpan(); 
                lifespan.setAge(age); 
                lifespanRepository.save(lifespan); 
            });
            Stream.of("ติดต่อ", "ไม่ติดต่อ").forEach(typename -> {
                Type type = new Type(); 
                type.setType(typename); 
                typeRepository.save(type); 
            });


            Stream.of("Normal Room", "ICU Room", "Operating Room", "Emergency  Room","Mortuary Room","Labor Room").forEach(room_name -> {
				PatientRoom patientRoom = new PatientRoom(); // สร้าง new Object patientRoom
				patientRoom.setName(room_name); // set ชื่อ (room_name) ให้ Object ชื่อ patientRoom
				patientRoomRepository.save(patientRoom); // บันทึก Objcet ชื่อ patientRoom
			});
			Stream.of("A1", "A2", "A3", "A4","A5","A6","B1","B2","B3","B4","B5","B6").forEach(zone_name -> {
				PatientZone patientZone = new PatientZone(); // สร้าง Object patientZone
				patientZone.setName(zone_name); // set ชื่อ (zone_name) ให้ Object ชื่อ patientZone
				patientZoneRepository.save(patientZone); // บันทึก Objcet ชื่อ patientZone
			});
			Stream.of("Aluminum", "Steel", "Wood").forEach(physBed_name -> {
				PhysicalBed physicalBed = new PhysicalBed(); // สร้าง Object physicalBed
				physicalBed.setName(physBed_name); // set ชื่อ (physBed_name) ให้ Object ชื่อ physicalBed
				physicalBedRepository.save(physicalBed); // บันทึก Objcet ชื่อ physicalBed
			});
			Stream.of("Doctor", "Nurse","Nutritionost","Housekeeper","Chef").forEach(position_name -> {
				Position position = new Position();
				position.setPosition_name(position_name);
				positionRepository.save(position);
			});
			Stream.of("Single", "Married", "Windowed", "Divorced","	Separated").forEach(maritalstatus_name -> {
				Maritalstatus maritalstatus = new Maritalstatus();
				maritalstatus.setMaritalstatus_name(maritalstatus_name);
				maritalstatusRepository.save(maritalstatus);
			});
			Stream.of("Primary", "Secondary", "Vocational ","University").forEach(educationlevel_name -> {
				Educationlevel educationlevel = new Educationlevel();
				educationlevel.setEducationlevel_name(educationlevel_name);
				educationlevelRepository.save(educationlevel);
			});
			Stream.of("Man", "Female").forEach(gen_name -> {
				Gender gender = new Gender();
				gender.setGen_name(gen_name);
				genderRepository.save(gender);
			});
			Stream.of("O", "A", "B","AB").forEach(blood_name -> {
				Bloodtype bloodtype = new Bloodtype();
				bloodtype.setBlood_name(blood_name);
				bloodtypeRepository.save(bloodtype);
			});
			genderRepository.findAll().forEach(System.out::println);
			bloodtypeRepository.findAll().forEach(System.out::println);
			positionRepository.findAll().forEach(System.out::println);
			maritalstatusRepository.findAll().forEach(System.out::println);
			educationlevelRepository.findAll().forEach(System.out::println);
		};
	}

}