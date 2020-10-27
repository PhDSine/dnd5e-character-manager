package com.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.character.service.api.SRDClassService;
import com.character.service.api.SRDRaceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SRDService {
	
	@Autowired
	SRDRaceService service;
	
	public void testSomething() {
		service.getAllRacesFromApiAndSave();
	}
}
