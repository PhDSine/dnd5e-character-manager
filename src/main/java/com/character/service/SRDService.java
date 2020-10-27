package com.character.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.character.mapper.SpellMapper;
import com.character.model.srd.SRDIndexNameUrl;
import com.character.model.srd.SRDResponse;
import com.character.model.srd.SRDSpell;
import com.character.persistence.entity.Spell;
import com.character.persistence.repository.SpellRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SRDService {
	
	@Autowired
	SpellRepository spellRepository;
	
	private static String BASE_URL = "https://www.dnd5eapi.co";

	public List<SRDSpell> getAllSpells() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDSpell> allSpells = new ArrayList<>();

		ResponseEntity<SRDResponse> spellListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/spells/?level=0,1,2,3,4,5,6,7,8,9", SRDResponse.class);
		SRDResponse spellListResponse = spellListResponseEntity.getBody();
		List<SRDIndexNameUrl> spellList = spellListResponse.getResults();
		
		spellList.parallelStream().forEach((spellUrl) -> {
			SRDSpell spell = restTemplate.getForEntity(BASE_URL + spellUrl.getUrl(), SRDSpell.class).getBody();
			allSpells.add(spell);
		});
		
		saveAllSpellsToDatabase(allSpells);

		return allSpells;
	}

	private void saveAllSpellsToDatabase(List<SRDSpell> allSpells) {
		for(int i = 0; i < allSpells.size(); i++) {
			Spell spell = SpellMapper.mapSpellEntityFromSRD(allSpells.get(i));

			boolean material = spell.isMaterial();
			log.info(allSpells.get(i).toString());
			log.info("{}", material);

			log.info(spell.toString());
			
			spellRepository.save(spell);
		}
		
	}
}
