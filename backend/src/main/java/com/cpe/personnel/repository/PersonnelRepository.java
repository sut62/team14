package com.cpe.personnel.repository;
import com.cpe.personnel.entity.Personnel;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource
public
interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findById(long id);

    @Query( value = "SELECT * FROM PERSONNEL p where p.telephone = :id and p.password = :pass",nativeQuery = true)
    Personnel findByPersonnel(@Param("id") String id,@Param("pass") String pass);

    @Query( value = "SELECT * FROM PERSONNEL p where p.telephone = :id and p.password = :pass and p.position_id = 2",nativeQuery = true)
    Personnel findNurseByPersonnel(@Param("id") String id,@Param("pass") String pass);

    @Query( value = "SELECT * FROM PERSONNEL p where p.telephone = :id and p.password = :pass and p.position_id = 1",nativeQuery = true)
    Personnel findDoctorByPersonnel(@Param("id") String id,@Param("pass") String pass);

    @Query( value = "SELECT * FROM PERSONNEL p where p.telephone = :id and p.password = :pass and p.position_id = 4",nativeQuery = true)
    Personnel findMedicalRecordsByPersonnel(@Param("id") String id,@Param("pass") String pass);
}