package com.character.mapper;

import java.util.List;

import com.character.model.srd.SRDAbility;
import com.character.persistence.entity.Ability;

public class AbilityMapper {

	public static Ability mapAbilityEntityFromSRD(SRDAbility srdAbility) {
		StringBuilder description = new StringBuilder();
		List<String> desc = srdAbility.getDesc();
		desc.stream().forEach(str -> {description.append(str + "\n"); });
		
		return Ability.builder()
				.shortAbilityName(srdAbility.getName())
				.abilityName(srdAbility.getFull_name())
				.description(description.toString())
				.build();
	}
}
