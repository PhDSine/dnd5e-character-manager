package com.character.model.srd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SRDArmor {
	private String index;
	private String name;
	private SRDEquipmentCategory equipment_category;
	private String armor_category;
	private SRDArmorClass armor_class;
	private int str_minimum;
	private boolean stealth_disadvantage;
	private int weight;
	private Cost cost;
	private String url;
}
