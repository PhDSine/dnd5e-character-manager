package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.mapper.SpellMapper;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDResponse;
import com.character.model.srd.SRDSpell;
import com.character.persistence.entity.Spell;
import com.character.persistence.repository.SpellRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDSpellService {
	
	@Autowired
	SpellRepository spellRepository;
	
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

	private void saveAllSpellsToDatabase(List<SRDSpell> allSpells) {
		allSpells.forEach(spell -> spellRepository.save(SpellMapper.mapSpellEntityFromSRD(spell)));
	}
}
