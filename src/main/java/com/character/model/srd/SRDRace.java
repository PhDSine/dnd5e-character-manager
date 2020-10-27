package com.character.model.srd;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SRDRace implements Serializable {

	private static final long serialVersionUID = 1L;

	private String index;
	private String name;
	private int speed;
	private List<SRDAbilityBonus> ability_bonuses;
	private String alignment;
	private String age;
	private String size;
	private String size_description;
	private List<APIReference> starting_proficiencies;
	private List<APIReference> languages;
	private String language_desc;
	private List<APIReference> traits;
	private List<APIReference> subraces;
	private String url;
}
