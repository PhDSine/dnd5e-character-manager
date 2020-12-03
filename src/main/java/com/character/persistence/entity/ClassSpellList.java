package com.character.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLASS_SPELL_LIST")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClassSpellList extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "parentClassId")
	ClassEntity classEntity;
	
	@ManyToOne
	@JoinColumn(name = "subclassId")
	Subclass subclass;
	
	@ManyToOne
	@JoinColumn(name = "spellId")
	Spell spell;
	
}
