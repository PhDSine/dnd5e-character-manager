package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.mapper.ArmorMapper;
import com.character.mapper.ProficiencyMapper;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDArmor;
import com.character.model.srd.SRDEquipmentCategory;
import com.character.model.srd.SRDProficiency;
import com.character.model.srd.SRDResponse;
import com.character.persistence.repository.ArmorRepository;
import com.character.persistence.repository.ProficiencyRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDProficiencyService {
	
	private static String BASE_URL = "https://www.dnd5eapi.co";
	
	@Autowired
	ProficiencyRepository proficiencyRepository;
	
	@Autowired
	ProficiencyMapper proficiencyMapper;
	
	public void getAllProficienciesAndSave() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDProficiency> allProficiencies = new ArrayList<>();
		
		ResponseEntity<SRDResponse> armorListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/proficiencies/", SRDResponse.class);
		SRDResponse proficiencyListResponse = armorListResponseEntity.getBody();
		List<APIReference> proficiencyList = proficiencyListResponse.getResults();
		
		proficiencyList.parallelStream().forEach((proficiencyUrl) -> {
			
			SRDProficiency proficiency = restTemplate.getForEntity(BASE_URL + proficiencyUrl.getUrl(), SRDProficiency.class).getBody();
			allProficiencies.add(proficiency);		
			
		});
		
		saveAllProficienciesToDatabase(allProficiencies);
		
	}

	private void saveAllProficienciesToDatabase(List<SRDProficiency> allProficiencies) {
		allProficiencies.forEach(proficiency ->
			proficiencyRepository.save(proficiencyMapper.mapProficiencyEntityFromSRD(proficiency)));
		
	}
}
