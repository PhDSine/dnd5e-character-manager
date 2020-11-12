package com.character.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.character.persistence.entity.Ability;

public interface AbilityRepository extends JpaRepository<Ability, Long> {

}
