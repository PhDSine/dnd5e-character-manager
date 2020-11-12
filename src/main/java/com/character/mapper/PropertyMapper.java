package com.character.mapper;

import java.util.List;

import com.character.model.srd.SRDWeaponProperty;
import com.character.persistence.entity.Property;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertyMapper {

	public static Property mapPropertyEntityFromSRD(SRDWeaponProperty srdWeaponProperty) {
		StringBuilder description = new StringBuilder();
		List<String> desc = srdWeaponProperty.getDesc();
		desc.stream().forEach(str -> {description.append(str + "\n"); });
		
		return Property.builder()
				.description(description.toString())
				.name(srdWeaponProperty.getName())
				.build();
	}
}
