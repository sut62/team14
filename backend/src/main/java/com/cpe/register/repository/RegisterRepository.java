package com.cpe.register.repository;

import com.cpe.register.entity.Register;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.data.repository.query.Param;

@RepositoryRestResource
public
interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findById(long id);
}