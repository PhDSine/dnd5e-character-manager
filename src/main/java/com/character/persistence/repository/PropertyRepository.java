package com.character.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.character.persistence.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
