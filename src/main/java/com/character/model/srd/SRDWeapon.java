package com.character.model.srd;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SRDWeapon {

	private String index;
	private String name;
	private APIReference equipment_category;
	private String weapon_category;
	private String weapon_range;
	private String category_range;

	private Cost cost;
	private SRDWeaponDamage damage;
	private SRDWeaponRange range;
	@JsonProperty("2h_damage")
	private SRDWeaponDamage twoh_damage;

	private int weight;
	private String url;
	
	
	private List<APIReference> properties;
}
