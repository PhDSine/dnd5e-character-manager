package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.mapper.AbilityMapper;
import com.character.mapper.SkillMapper;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDAbility;
import com.character.model.srd.SRDClass;
import com.character.model.srd.SRDResponse;
import com.character.model.srd.SRDSkill;
import com.character.persistence.entity.Ability;
import com.character.persistence.entity.Skill;
import com.character.persistence.repository.AbilityRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDAbilityService {
	
	@Autowired
	AbilityRepository abilityRepo;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static String BASE_URL = "https://www.dnd5eapi.co";

	@Transactional
	public List<SRDAbility> getAllAbilities() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDAbility> allAbilities = new ArrayList<>();

		ResponseEntity<SRDResponse> abilityListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/ability-scores/", SRDResponse.class);
		SRDResponse abilityListResponse = abilityListResponseEntity.getBody();
		List<APIReference> abilityList = abilityListResponse.getResults();
		
		abilityList.parallelStream().forEach((abilityUrl) -> {
			SRDAbility abilityObject = restTemplate.getForEntity(BASE_URL + abilityUrl.getUrl(), SRDAbility.class).getBody();
			allAbilities.add(abilityObject);
			
		});
		
		//saveAbilitiesToDatabase(allAbilities);
		
		getAllSkillsAndSaveToDatabase(allAbilities);

		return allAbilities;
	}

	private void getAllSkillsAndSaveToDatabase(List<SRDAbility> allAbilities) {
		RestTemplate restTemplate = new RestTemplate();

		for(SRDAbility ability : allAbilities) {
			List<APIReference> skills = ability.getSkills();
			List<SRDSkill> allSkills = new ArrayList<>();
			
			for(APIReference skill : skills) {
				ResponseEntity<SRDSkill> skillResponseEntity = 
						restTemplate.getForEntity(BASE_URL + skill.getUrl(), SRDSkill.class);
				SRDSkill srdSkill = skillResponseEntity.getBody();
				allSkills.add(srdSkill);
			}
			
			saveSkillsToDatabase(allSkills);
			
		}
		
	}

	@Transactional
	private void saveSkillsToDatabase(List<SRDSkill> allSkills) {
		for(SRDSkill skill : allSkills) {
			String abilityName = skill.getAbility_score().getName();
			
			Ability ability = getAbilityFromDatabase(abilityName);
			
			Skill skillEntity = SkillMapper.mapSkillEntityFromSRD(skill);
			log.info(skillEntity.getSkillName());
			log.info(ability.getAbilityName());
			skillEntity.setParentAbility(ability);
			
			entityManager.persist(skillEntity);
			
		}
	}

	private Ability getAbilityFromDatabase(String abilityName) {
		
		Ability ability = null;
		
		if("INT".equalsIgnoreCase(abilityName)) {
			ability = entityManager.getReference(Ability.class, 1L);
		} else if ("CON".equalsIgnoreCase(abilityName)) {
			ability = entityManager.getReference(Ability.class, 2L);
		} else if ("WIS".equalsIgnoreCase(abilityName)) {
			ability = entityManager.getReference(Ability.class, 3L);
		} else if ("CHA".equalsIgnoreCase(abilityName)) {
			ability = entityManager.getReference(Ability.class, 4L);
		} else if ("DEX".equalsIgnoreCase(abilityName)) {
			ability = entityManager.getReference(Ability.class, 5L);
		} else if ("STR".equalsIgnoreCase(abilityName)) {
			ability = entityManager.getReference(Ability.class, 6L);
		}
		return ability;
	}

	private void saveAbilitiesToDatabase(List<SRDAbility> allAbilities) {
		allAbilities.forEach(ability -> abilityRepo.save(AbilityMapper.mapAbilityEntityFromSRD(ability)));
	}
}
