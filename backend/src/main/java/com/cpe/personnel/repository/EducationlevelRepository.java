package com.cpe.personnel.repository;
import com.cpe.personnel.entity.Educationlevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface EducationlevelRepository extends JpaRepository<Educationlevel, Long> {
    Educationlevel findById(long id);
}