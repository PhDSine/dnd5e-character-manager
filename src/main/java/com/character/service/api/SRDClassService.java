package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.model.srd.SRDClass;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDClassService {
	
	private static String BASE_URL = "https://www.dnd5eapi.co";

	public List<SRDClass> getAllClassesFromApiAndSave() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDClass> allClasses = new ArrayList<>();

		ResponseEntity<SRDResponse> classListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/classes/", SRDResponse.class);
		SRDResponse classListResponse = classListResponseEntity.getBody();
		List<APIReference> classList = classListResponse.getResults();
		
		classList.parallelStream().forEach((classUrl) -> {
			SRDClass classObject = restTemplate.getForEntity(BASE_URL + classUrl.getUrl(), SRDClass.class).getBody();
			allClasses.add(classObject);
			
			// TODO: remove log
			log.info(classObject.toString());
		});

		return allClasses;
	}
}
