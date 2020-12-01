package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.model.srd.SRDClass;
import com.character.mapper.ClassEntityMapper;
import com.character.model.srd.APIReference;
import com.character.model.srd.SRDResponse;
import com.character.model.srd.SRDSubclass;
import com.character.persistence.entity.ClassEntity;
import com.character.persistence.entity.Subclass;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDClassService {
	
	private static String BASE_URL = "https://www.dnd5eapi.co";
	
	@Autowired
	ClassEntityMapper classEntityMapper;
	
	@PersistenceContext
	EntityManager entityManager;

	public List<SRDClass> getAllClasses() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDClass> allClasses = new ArrayList<>();

		ResponseEntity<SRDResponse> classListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/classes/", SRDResponse.class);
		SRDResponse classListResponse = classListResponseEntity.getBody();
		List<APIReference> classList = classListResponse.getResults();
		
		classList.parallelStream().forEach((classUrl) -> {
			SRDClass classObject = restTemplate.getForEntity(BASE_URL + classUrl.getUrl(), SRDClass.class).getBody();
			allClasses.add(classObject);
			
		});
		
		saveAllClassesToDatabase(allClasses);
		
		saveAllSubclassesToDatabase(allClasses);

		return allClasses;
	}

	private void saveAllClassesToDatabase(List<SRDClass> classList) {
		for(SRDClass srdClass : classList) {
			ClassEntity classEntity = classEntityMapper.mapClassEntityFromSRD(srdClass);
			entityManager.persist(classEntity);
		}
		
	}
	
	private void saveAllSubclassesToDatabase(List<SRDClass> classList) {
		for(SRDClass srdClass : classList) {
			List<APIReference> subclasses = srdClass.getSubclasses();
			for(APIReference subclassUrl : subclasses) {
				RestTemplate restTemplate = new RestTemplate();
				SRDSubclass subclassObject = restTemplate.getForEntity(BASE_URL + subclassUrl.getUrl(), SRDSubclass.class).getBody();
				Subclass subclass = classEntityMapper.mapSubclassEntityFromSRD(subclassObject);
				entityManager.persist(subclass);
			}
		}
		
	}
}
