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
}