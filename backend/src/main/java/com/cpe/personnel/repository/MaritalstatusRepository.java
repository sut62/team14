package com.cpe.personnel.repository;
import com.cpe.personnel.entity.Maritalstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MaritalstatusRepository extends JpaRepository<Maritalstatus, Long> {
	Maritalstatus findById(long id);
}