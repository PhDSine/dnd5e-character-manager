package com.character.mapper;

import java.util.Arrays;

import com.character.model.srd.SRDDamage;
import com.character.model.srd.SRDNameUrl;
import com.character.model.srd.SRDSpell;
import com.character.persistence.entity.Spell;

public class SpellMapper {

	public static Spell mapSpellEntityFromSRD(SRDSpell srdSpell) {
		String[] components = srdSpell.getComponents();
		boolean isVerbal = Arrays.stream(components).anyMatch("V"::equals);
		boolean isSomatic = Arrays.stream(components).anyMatch("S"::equals);
		boolean isMaterial = Arrays.stream(components).anyMatch("M"::equals);
		String higherLevel = srdSpell.getHigher_level() == null ? null : srdSpell.getHigher_level().get(0);
		String damageType = getDamageType(srdSpell);
		
		return Spell.builder()
				.indexName(srdSpell.getIndex())
				.spellName(srdSpell.getName())
				.description(srdSpell.getDesc().get(0))
				.higherLevel(higherLevel)
				.spellRange(srdSpell.getRange())
				.isVerbal(isVerbal)
				.isSomatic(isSomatic)
				.isMaterial(isMaterial)
				.isRitual(srdSpell.isRitual())
				.material(srdSpell.getMaterial())
				.duration(srdSpell.getDuration())
				.requiresConcentration(srdSpell.isConcentration())
				.castingTime(srdSpell.getCasting_time())
				.spellLevel(Integer.parseInt(srdSpell.getLevel()))
				.attackType(srdSpell.getAttack_type())
				.damageType(damageType)
				.build();
	}

	private static String getDamageType(SRDSpell srdSpell) {
		SRDDamage damage = srdSpell.getDamage();
		if(damage == null) {
			return null;
		}
		SRDNameUrl damageType = damage.getDamage_type();
		if(damageType == null) {
			return null;
		}
		String damageTypeName = damageType.getName();
		if(damageTypeName == null) {
			return null;
			
		}
		return damageTypeName;
	}
}
