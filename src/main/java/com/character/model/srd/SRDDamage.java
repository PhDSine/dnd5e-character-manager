package com.character.model.srd;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SRDDamage {

	private SRDNameUrl damage_type;
	private Map<Integer, String> damage_at_slot_level;
	
}
