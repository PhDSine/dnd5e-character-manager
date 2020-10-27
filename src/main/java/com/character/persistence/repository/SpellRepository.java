package com.character.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.character.persistence.entity.Spell;

public interface SpellRepository extends JpaRepository<Spell, Long> {

}
