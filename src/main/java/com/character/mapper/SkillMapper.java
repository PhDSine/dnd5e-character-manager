package com.character.mapper;

import java.util.List;

import com.character.model.srd.SRDSkill;
import com.character.persistence.entity.Skill;

public class SkillMapper {

	public static Skill mapSkillEntityFromSRD(SRDSkill srdSkill) {
		StringBuilder description = new StringBuilder();
		List<String> desc = srdSkill.getDesc();
		desc.stream().forEach(str -> {description.append(str + "\n"); });
		
		return Skill.builder()
				.skillName(srdSkill.getName())
				.description(description.toString())
				.build();
	}
}
