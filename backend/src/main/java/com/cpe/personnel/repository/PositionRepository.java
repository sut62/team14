package com.cpe.personnel.repository;
import com.cpe.personnel.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public
interface PositionRepository extends JpaRepository<Position, Long> {
	Position findById(long id);
}