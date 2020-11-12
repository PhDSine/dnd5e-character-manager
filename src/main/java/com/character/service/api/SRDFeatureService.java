package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.model.srd.APIReference;
import com.character.model.srd.SRDFeature;
import com.character.model.srd.SRDResponse;
import com.character.model.srd.SRDSpell;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDFeatureService {
	
	private static String BASE_URL = "https://www.dnd5eapi.co";

	public List<SRDFeature> getAllFeatures() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDFeature> allFeatures = new ArrayList<>();

		ResponseEntity<SRDResponse> featureListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/features/", SRDResponse.class);
		SRDResponse featureListResponse = featureListResponseEntity.getBody();
		List<APIReference> featureList = featureListResponse.getResults();
		
		featureList.parallelStream().forEach((featureUrl) -> {
			SRDFeature feature = restTemplate.getForEntity(BASE_URL + featureUrl.getUrl(), SRDFeature.class).getBody();
			allFeatures.add(feature);
			log.info("{}", feature);
		});

		return allFeatures;
	}
}
