package com.character.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.character.persistence.entity.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

}
