package com.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.character.service.api.SRDClassService;
import com.character.service.api.SRDEquipmentService;
import com.character.service.api.SRDRaceService;
import com.character.service.api.SRDSpellService;

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
	
	public void loadDatabases() {
		equipmentService.getAllEquipment();
	}
}
