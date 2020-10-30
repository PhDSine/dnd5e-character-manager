package com.character.model.srd;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SRDEquipment {

	private String index;
	private String name;
	private APIReference equipment_category;
	private APIReference gear_category;
	private Cost cost;
	private int weight;
	private String url;
	
	private String weapon_category;
	private String weapon_range;
	private String category_range;
	private SRDWeaponDamage damage;
	private SRDWeaponRange range;
	
	private List<APIReference> properties;
}
