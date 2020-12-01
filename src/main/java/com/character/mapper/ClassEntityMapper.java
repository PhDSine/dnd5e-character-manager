package com.character.mapper;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.character.model.srd.SRDClass;
import com.character.model.srd.SRDSubclass;
import com.character.persistence.entity.ClassEntity;
import com.character.persistence.entity.Subclass;
import com.character.persistence.repository.ClassRepository;

@Component
public class ClassEntityMapper {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	ClassRepository classRepo;

	public ClassEntity mapClassEntityFromSRD(SRDClass srdClass) {
		return ClassEntity.builder()
				.className(srdClass.getName())
				.description("")
				.hitDie(srdClass.getHit_die())
				.spellcastingAbility(getSpellcastingAbility(srdClass))
				.build();
	}

	private String getSpellcastingAbility(SRDClass srdClass) {
		if(srdClass.getSpellcasting() != null) {
			if(srdClass.getSpellcasting().getSpellcasting_ability() != null) {
				if(srdClass.getSpellcasting().getSpellcasting_ability().getName() != null) {
					return srdClass.getSpellcasting().getSpellcasting_ability().getName();
				}
			}
		}
		return null;
	}
	
	public Subclass mapSubclassEntityFromSRD(SRDSubclass srdSubclass) {
		StringBuilder description = new StringBuilder();
		List<String> descriptionList = srdSubclass.getDesc();
		for(String desc : descriptionList) {
			description.append(desc);
		}
		
		ClassEntity parentClass = getParentClass(srdSubclass);
		
		return Subclass.builder()
				.name(srdSubclass.getName())
				.parentClass(parentClass)
				.description(description.toString())
				.build();
	}

	private ClassEntity getParentClass(SRDSubclass srdSubclass) {
		String parentClassName = srdSubclass.getClassReference().getName();
		Optional<ClassEntity> parentClass = classRepo.findByClassName(parentClassName);
		return parentClass.get();
	}
}
