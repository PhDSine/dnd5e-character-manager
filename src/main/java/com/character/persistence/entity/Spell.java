package com.character.persistence.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Spell extends BaseEntity {
	
	@OneToMany(mappedBy = "spell")
	private Set<ClassSpellList> classSpells;
	
	private String indexName;
	private String spellName;
	private String description;
	private String higherLevel;
	private String spellRange;
	private boolean isVerbal;
	private boolean isSomatic;
	private boolean isMaterial;
	private boolean isRitual;
	private String material;
	private String duration;
	private boolean requiresConcentration;
	private String castingTime;
	private int spellLevel;
	private String attackType;
	private String damageType;
}
