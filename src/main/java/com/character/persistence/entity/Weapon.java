package com.character.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Weapon extends BaseEntity {
	
	@ManyToMany
	@JoinTable(
			  name = "weapon_properties", 
			  joinColumns = @JoinColumn(name = "weaponId"), 
			  inverseJoinColumns = @JoinColumn(name = "propertyId"))
	Set<Property> properties;
	
	private String weaponName;
	private String weaponCategory;
	private String weaponRange;
	private String categoryRange;
	private String cost;
	private String damageDice;
	private String twoHandedDamageDice;
	private String damageType;
	private Integer normalRange;
	private Integer longRange;
	private int weight;
	

}
