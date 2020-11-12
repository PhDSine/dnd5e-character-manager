package com.character.mapper;

import org.springframework.stereotype.Component;

import com.character.model.srd.SRDProficiency;
import com.character.persistence.entity.Proficiency;

@Component
public class ProficiencyMapper {

	public Proficiency mapProficiencyEntityFromSRD(SRDProficiency proficiency) {
		return Proficiency.builder().proficiencyName(proficiency.getName()).build();
	}

}
