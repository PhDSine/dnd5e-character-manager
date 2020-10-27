package com.character.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.character.persistence.entity.Race;

public interface RaceRepository extends JpaRepository<Race, Long> {

}
