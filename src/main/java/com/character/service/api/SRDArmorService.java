package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.mapper.ArmorMapper;
import com.character.mapper.PropertyMapper;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDArmor;
import com.character.model.srd.SRDEquipmentCategory;
import com.character.persistence.repository.ArmorRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDArmorService {
	
	private static String BASE_URL = "https://www.dnd5eapi.co";
	
	@Autowired
	ArmorRepository armorRepository;
	
	@Autowired
	ArmorMapper armorMapper; 
	
	public void getAllArmorsAndSave() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDArmor> allArmors = new ArrayList<>();
		
		ResponseEntity<SRDEquipmentCategory> armorListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/equipment-categories/armor/", SRDEquipmentCategory.class);
		SRDEquipmentCategory armorListResponse = armorListResponseEntity.getBody();
		List<APIReference> armorList = armorListResponse.getEquipment();
		
		armorList.parallelStream().forEach((armorUrl) -> {
			if(armorUrl.getUrl().contains("/equipment")) {
				SRDArmor armor = restTemplate.getForEntity(BASE_URL + armorUrl.getUrl(), SRDArmor.class).getBody();
				allArmors.add(armor);
				log.info(armor.toString());
			}
			
		});
		
		saveAllArmorsToDatabase(allArmors);
		
	}

	private void saveAllArmorsToDatabase(List<SRDArmor> allArmors) {
		allArmors.forEach(armor -> armorRepository.save(armorMapper.mapArmorEntityFromSRD(armor)));
		
	}
}
