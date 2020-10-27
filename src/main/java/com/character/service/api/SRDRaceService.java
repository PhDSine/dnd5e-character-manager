package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.model.srd.APIReference;
import com.character.model.srd.SRDClass;
import com.character.model.srd.SRDRace;
import com.character.model.srd.SRDResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDRaceService {
	private static String BASE_URL = "https://www.dnd5eapi.co";

	public List<SRDRace> getAllRacesFromApiAndSave() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDRace> allRaces = new ArrayList<>();

		ResponseEntity<SRDResponse> raceListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/races/", SRDResponse.class);
		SRDResponse raceListResponse = raceListResponseEntity.getBody();
		List<APIReference> raceList = raceListResponse.getResults();
		
		raceList.parallelStream().forEach((raceUrl) -> {
			SRDRace raceObject = restTemplate.getForEntity(BASE_URL + raceUrl.getUrl(), SRDRace.class).getBody();
			allRaces.add(raceObject);
			
			// TODO: remove log
			log.info(raceObject.toString());
		});

		return allRaces;
	}
}
