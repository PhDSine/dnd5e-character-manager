package com.character.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.character.persistence.entity.Proficiency;

public interface ProficiencyRepository extends JpaRepository<Proficiency, Long> {

}
