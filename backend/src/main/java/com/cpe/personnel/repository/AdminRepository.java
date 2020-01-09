package com.cpe.personnel.repository;
import com.cpe.personnel.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findById(long id);
}