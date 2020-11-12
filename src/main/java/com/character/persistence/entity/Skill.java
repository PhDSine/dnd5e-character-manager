package com.character.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Skill extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String skillName;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="parentAbilityId", nullable=false)
    @ToString.Exclude
	private Ability parentAbility;
}
