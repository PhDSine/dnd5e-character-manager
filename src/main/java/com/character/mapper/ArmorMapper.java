package com.character.mapper;

import org.springframework.stereotype.Component;

import com.character.model.srd.SRDArmor;
import com.character.persistence.entity.Armor;

@Component
public class ArmorMapper {

	public Armor mapArmorEntityFromSRD(SRDArmor srdArmor) {
		
		return Armor.builder()
				.armorCategory(srdArmor.getArmor_category())
				.armorName(srdArmor.getName())
				.baseAc(srdArmor.getArmor_class().getBase())
				.cost(String.valueOf(srdArmor.getCost() != null ? srdArmor.getCost().getQuantity() + " " + srdArmor.getCost().getUnit(): "free"))
				.hasDexterityBonus(srdArmor.getArmor_class().getDex_bonus())
				.hasStealthDisadvantage(srdArmor.isStealth_disadvantage())
				.maxAc(srdArmor.getArmor_class().getMax_bonus())
				.strengthMinimum(srdArmor.getStr_minimum())
				.build();
	}
}
