package com.character.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.character.persistence.entity.Subclass;

@Repository
public interface SubclassRepository extends JpaRepository<Subclass, Long>{

	Optional<Subclass> findByName(String name);
	
}
