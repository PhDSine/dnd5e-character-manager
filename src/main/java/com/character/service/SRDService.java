package com.character.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.character.service.api.SRDAbilityService;
import com.character.service.api.SRDArmorService;
import com.character.service.api.SRDClassService;
import com.character.service.api.SRDEquipmentService;
import com.character.service.api.SRDFeatureService;
import com.character.service.api.SRDProficiencyService;
import com.character.service.api.SRDRaceService;
import com.character.service.api.SRDSpellService;
import com.character.service.api.SRDWeaponService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SRDService {
	
	@Autowired
	SRDEquipmentService equipmentService;
	
	@Autowired
	SRDClassService classService;
	
	@Autowired
	SRDRaceService raceServce;
	
	@Autowired
	SRDSpellService spellService;
	
	@Autowired
	SRDFeatureService featureService;
	
	@Autowired
	SRDAbilityService abilityService;
	
	@Autowired
	SRDWeaponService weaponService;
	
	@Autowired
	SRDArmorService armorService;
	
	@Autowired
	SRDProficiencyService proficiencyService;
	
	@Transactional
	public void loadDatabases() {
//		featureService.getAllFeatures();
//		classService.getAllClasses();
//		abilityService.getAllAbilities(); // DONE ALL SKILLS AND ABILITIES ARE SAVED
//		weaponService.getAllWeaponPropertiesAndSave();
//		weaponService.getAllWeaponsAndSave(); // ALL WEAPONS ARE SAVED, ALL PROPERTIES ARE SAVED, JOIN TABLE IS SAVED
// 		armorService.getAllArmorsAndSave(); // ALL ARMORS ARE SAVED
//		proficiencyService.getAllProficienciesAndSave(); ALL PROFICIENCIES SAVED; NOT CONNECTED TO ANY CLASS
	}
}
