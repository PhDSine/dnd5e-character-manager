package com.character.persistence.entity;

import java.util.List;

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
public class Armor extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String armorName;
	private String armorCategory;
	private int baseAc;
	private Integer maxAc;
	private boolean hasDexterityBonus;
	private String cost;
	private int weight;
	private int strengthMinimum;
	private boolean hasStealthDisadvantage;
}
