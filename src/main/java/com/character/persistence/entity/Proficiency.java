package com.character.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
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
public class Proficiency extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String proficiencyName;
}
