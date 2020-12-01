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
public class SRDClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private String index;
	private String name;
	private Integer hit_die;
	private List<SRDProficiencyChoices> proficiency_choices;
	private List<APIReference> proficiencies;
	private List<APIReference> saving_throws;
	private String starting_equipment;
	private String class_levels;
	private List<APIReference> subclasses;
	private SRDSpellcasting spellcasting;
	private String spells;
	private String url;

}
