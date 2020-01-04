package com.cpe;

import com.cpe.DiseasePackage.entity.*;
import com.cpe.DiseasePackage.repository.*;
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
	
    ApplicationRunner init(TypeRepository typeRepository,LifeSpanRepository lifespanRepository ) {
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
		};
	}

}