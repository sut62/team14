package com.cpe.personnel.repository;
import com.cpe.personnel.entity.Admin;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
@RepositoryRestResource
public
interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findById(long id);
    @Query( value = "SELECT * FROM ADMIN a where a.user = :id and a.password = :pass ",nativeQuery = true)
    Admin findAdmin(@Param("id") String user,@Param("pass") String pass);

}