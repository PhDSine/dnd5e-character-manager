package com.character.service.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.mapper.SpellMapper;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDNameUrl;
import com.character.model.srd.SRDResponse;
import com.character.model.srd.SRDSpell;
import com.character.persistence.entity.ClassEntity;
import com.character.persistence.entity.ClassSpellList;
import com.character.persistence.entity.Spell;
import com.character.persistence.entity.Subclass;
import com.character.persistence.repository.ClassRepository;
import com.character.persistence.repository.SpellRepository;
import com.character.persistence.repository.SubclassRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDSpellService {
	
	@Autowired
	SpellMapper spellMapper;
	
	@Autowired
	SpellRepository spellRepo;
	
	@Autowired
	ClassRepository classRepo;
	
	@Autowired
	SubclassRepository subclassRepo;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static String BASE_URL = "https://www.dnd5eapi.co";

	public List<SRDSpell> getAllSpellsFromApiAndSave() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDSpell> allSpells = new ArrayList<>();

		ResponseEntity<SRDResponse> spellListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/spells/?level=0,1,2,3,4,5,6,7,8,9", SRDResponse.class);
		SRDResponse spellListResponse = spellListResponseEntity.getBody();
		List<APIReference> spellList = spellListResponse.getResults();
		
		spellList.parallelStream().forEach((spellUrl) -> {
			SRDSpell spell = restTemplate.getForEntity(BASE_URL + spellUrl.getUrl(), SRDSpell.class).getBody();
			allSpells.add(spell);
		});
		
		saveAllSpellsToDatabase(allSpells);

		return allSpells;
	}

	@Transactional
	private void saveAllSpellsToDatabase(List<SRDSpell> allSpells) {
//		int attackType = 0;
//		int castingTime = 0;
//		int damageType = 0;
//		int description = 0;
//		int duration = 0;
//		int higherLevel = 0;
//		int indexName = 0;
//		int material = 0;
//		int spellName = 0;
//		int spellRange = 0;

		for(SRDSpell srdSpell : allSpells) {
//			Spell spell = spellMapper.mapSpellEntityFromSRD(srdSpell);
//			if(spell.getAttackType() != null && spell.getAttackType().length() > attackType) {
//				attackType = spell.getAttackType().length();
//			}
//			
//			if(spell.getCastingTime() != null && spell.getCastingTime().length() > castingTime) {
//				castingTime = spell.getCastingTime().length();
//			}
//			
//			if(spell.getDamageType() != null && spell.getDamageType().length() > damageType) {
//				damageType = spell.getDamageType().length();
//			}
//			
//			if(spell.getDescription() != null && spell.getDescription().length() > description) {
//				description = spell.getDescription().length();
//			}
//			
//			if(spell.getDuration() != null && spell.getDuration().length() > duration) {
//				duration = spell.getDuration().length();
//			}
//			
//			if(spell.getHigherLevel() != null && spell.getHigherLevel().length() > higherLevel) {
//				higherLevel = spell.getHigherLevel().length();
//			}
//			
//			if(spell.getIndexName() != null && spell.getIndexName().length() > indexName) {
//				indexName = spell.getIndexName().length();
//			}
//			
//			if(spell.getMaterial() != null && spell.getMaterial().length() > material) {
//				material = spell.getMaterial().length();
//			}
//			
//			if(spell.getSpellName() != null && spell.getSpellName().length() > spellName) {
//				spellName = spell.getSpellName().length();
//			}
			
		

			// Save spell to db
			Spell spell = spellRepo.save(spellMapper.mapSpellEntityFromSRD(srdSpell));
			
			// Save record for each class
			List<SRDNameUrl> classes = srdSpell.getClasses();
			for(SRDNameUrl classNameUrl : classes) {
				String className = classNameUrl.getName();
				Optional<ClassEntity> classEntity = classRepo.findByClassName(className);
				if(classEntity.isPresent()) {
					ClassSpellList classSpellList = 
							ClassSpellList.builder()
								.classEntity(classEntity.get())
								.spell(spell)
								.build();
					entityManager.persist(classSpellList);
				}
			}
			
			// Save record for each subclass
			List<SRDNameUrl> subclasses = srdSpell.getSubclasses();
			for(SRDNameUrl subclassNameUrl : subclasses) {
				String subclassName = subclassNameUrl.getName();
				Optional<Subclass> subclass = subclassRepo.findByName(subclassName);
				if(subclass.isPresent()) {
					ClassSpellList classSpellList = 
							ClassSpellList.builder()
								.subclass(subclass.get())
								.spell(spell)
								.build();
					entityManager.persist(classSpellList);
				}
			}
			
		}
		
//		log.info("attackType: {}", attackType);
//		log.info("castingTime: {}", castingTime);
//		log.info("damageType: {}", damageType);
//		log.info("description: {}", description);
//		log.info("duration: {}", duration);
//		log.info("higherLevel: {}", higherLevel);
//		log.info("indexName: {}", indexName);
//		log.info("material: {}", material);
//		log.info("spellName: {}", spellName);
//		log.info("spellRange: {}", spellRange);
	}
}
