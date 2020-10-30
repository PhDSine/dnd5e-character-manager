package com.character.model.srd;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SRDEquipmentCategory {
	
	private String index;
	private String name;
	private List<APIReference> equipment;
	
}
