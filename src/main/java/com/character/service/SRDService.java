package com.character.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.character.service.api.SRDClassService;
import com.character.service.api.SRDSpellService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SRDService {
	
	@Autowired
	SRDClassService classService;
	
	@Autowired
	SRDSpellService spellService;
	
	@Transactional
	public void loadDatabases() {
//		classService.getAllClasses(); All classes and subclasses in SRD have been loaded
//		spellService.getAllSpellsFromApiAndSave();
	}
}
