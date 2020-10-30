package com.character.service.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.character.model.srd.APIReference;
import com.character.model.srd.SRDEquipment;
import com.character.model.srd.SRDEquipmentCategories;
import com.character.model.srd.SRDEquipmentCategory;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SRDEquipmentService {

	private static String BASE_URL = "https://www.dnd5eapi.co";

	public List<SRDEquipment> getAllEquipment() {
		RestTemplate restTemplate = new RestTemplate();
		List<SRDEquipment> allEquipment = new ArrayList<>();
		
		ResponseEntity<SRDEquipmentCategories> equipmentCategoryListResponseEntity = 
				restTemplate.getForEntity(BASE_URL + "/api/equipment-categories/", SRDEquipmentCategories.class);
		
		List<APIReference> equipmentCategories = equipmentCategoryListResponseEntity.getBody().getResults();
		
		for(int i = 0; i < equipmentCategories.size() ; i++) {
			APIReference equipmentCategory = equipmentCategories.get(i);
			String equipmentCategoryUrl = equipmentCategory.getUrl();
			
			// This url throws a 404, TODO: change to a try-catch
			if(!equipmentCategoryUrl.equalsIgnoreCase("/api/equipment-categories/wondrous-item")) {
				ResponseEntity<SRDEquipmentCategory> equipmentList = 
						restTemplate.getForEntity(BASE_URL + equipmentCategoryUrl, SRDEquipmentCategory.class);
				
				SRDEquipmentCategory equipmentCategoryList = equipmentList.getBody();
				List<APIReference> equipments = equipmentCategoryList.getEquipment();
				
				for(int j = 0; j < equipments.size() ; j++) {
					String equipmentUrl = equipments.get(j).getUrl();
					ResponseEntity<SRDEquipment> equipment =
							restTemplate.getForEntity(BASE_URL + equipmentUrl, SRDEquipment.class);
					
					allEquipment.add(equipment.getBody());
				}

			}
			
		}

		return allEquipment;
	}
}
