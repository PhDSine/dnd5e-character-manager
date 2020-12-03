package com.character.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.character.persistence.entity.Spell;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {

	List<Spell> findByOrderBySpellName();
}
