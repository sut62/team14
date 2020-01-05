package com.cpe;

import com.cpe.DiseasePackage.entity.*;
import com.cpe.DiseasePackage.repository.*;
import com.cpe.patientBed.entity.*;
import com.cpe.patientBed.repository.*;
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
    ,PatientRoomRepository patientRoomRepository,PatientZoneRepository patientZoneRepository, PhysicalBedRepository physicalBedRepository) {
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

		};
	}

}