package com.character.persistence.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
public class Property extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy = "properties")
	Set<Weapon> weapons;

	private String name;
	private String description;
}
