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

    @Query( value = "SELECT * FROM PERSONNEL p where p.personnel_id = :id and p.telephone = :call and p.position_id = 3",nativeQuery = true)
    Personnel findNutritionostByPersonnel(@Param("id") long id,@Param("call") String call);

    @Query( value = "SELECT * FROM PERSONNEL p where p.personnel_id = :id and p.telephone = :call and p.position_id = 2",nativeQuery = true)
    Personnel findNurseByPersonnel(@Param("id") long id,@Param("call") String call);

    @Query( value = "SELECT * FROM PERSONNEL p where p.personnel_id = :id and p.telephone = :call and p.position_id = 1",nativeQuery = true)
    Personnel findDoctorByPersonnel(@Param("id") long id,@Param("call") String call);
}