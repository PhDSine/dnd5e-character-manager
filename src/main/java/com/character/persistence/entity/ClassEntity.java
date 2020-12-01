package com.character.persistence.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLASS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClassEntity extends BaseEntity {
	
	@OneToMany(mappedBy = "classEntity")
	private Set<ClassSpellList> classSpells;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="parentClass")
	protected List<Subclass> subclasses;

	protected String className;
	protected String description;
	protected int hitDie;
	protected String spellcastingAbility;
}
