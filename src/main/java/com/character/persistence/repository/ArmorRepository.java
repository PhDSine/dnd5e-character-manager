package com.character.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.character.persistence.entity.Armor;

public interface ArmorRepository extends JpaRepository<Armor, Long> {

}
