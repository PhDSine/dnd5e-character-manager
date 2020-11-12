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
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ability extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String shortAbilityName;
	private String abilityName;
	private String description;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="parentAbility")
	private List<Skill> skills;
	
}
