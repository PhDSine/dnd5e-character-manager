package com.character.service.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.mapper.PropertyMapper;
import com.character.mapper.SpellMapper;
import com.character.mapper.WeaponMapper;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDEquipmentCategory;
import com.character.model.srd.SRDResponse;
import com.character.model.srd.SRDSpell;
import com.character.model.srd.SRDWeapon;
import com.character.model.srd.SRDWeaponProperty;
import com.character.persistence.entity.Ability;
import com.character.persistence.entity.Property;
import com.character.persistence.entity.Weapon;
import com.character.persistence.repository.PropertyRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDWeaponService {
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	WeaponMapper weaponMapper;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static String BASE_URL = "https://www.dnd5eapi.co";

	public void getAllWeaponPropertiesAndSave() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDWeaponProperty> allWeaponProperties = new ArrayList<>();
		
		ResponseEntity<SRDResponse> weaponPropertyListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/weapon-properties/", SRDResponse.class);
		SRDResponse weaponPropertyListResponse = weaponPropertyListResponseEntity.getBody();
		List<APIReference> weaponPropertyList = weaponPropertyListResponse.getResults();
		
		weaponPropertyList.parallelStream().forEach((weaponPropertyUrl) -> {
			SRDWeaponProperty weaponProperty = restTemplate.getForEntity(BASE_URL + weaponPropertyUrl.getUrl(), SRDWeaponProperty.class).getBody();
			allWeaponProperties.add(weaponProperty);
		});
		
		saveAllWeaponPropertiesToDatabase(allWeaponProperties);

	}

	private void saveAllWeaponPropertiesToDatabase(List<SRDWeaponProperty> allWeaponProperties) {
		allWeaponProperties.forEach(spell -> propertyRepository.save(PropertyMapper.mapPropertyEntityFromSRD(spell)));
	}
	
	public void getAllWeaponsAndSave() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDWeapon> allWeapons = new ArrayList<>();
		
		ResponseEntity<SRDEquipmentCategory> weaponListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/equipment-categories/weapon/", SRDEquipmentCategory.class);
		SRDEquipmentCategory weaponListResponse = weaponListResponseEntity.getBody();
		List<APIReference> weaponList = weaponListResponse.getEquipment();
		
		weaponList.parallelStream().forEach((weaponUrl) -> {
			if(weaponUrl.getUrl().contains("/equipment")) {
				SRDWeapon weaponProperty = restTemplate.getForEntity(BASE_URL + weaponUrl.getUrl(), SRDWeapon.class).getBody();
				allWeapons.add(weaponProperty);
			}
			
		});
		
		saveAllWeaponsToDatabase(allWeapons);

	}

	private void saveAllWeaponsToDatabase(List<SRDWeapon> allWeapons) {
		for(SRDWeapon weapon : allWeapons) {
			
			Weapon weaponEntity = weaponMapper.mapWeaponEntityFromSRD(weapon);
			
			entityManager.persist(weaponEntity);
		}
	}

	
}
