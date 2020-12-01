package com.character.model.srd;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SRDSubclass {

	private String index;
	@JsonProperty("class")
	private APIReference classReference;
	private String name;
	private String subclass_flavor;
	private List<String> desc;
	private List<SRDSpells> spells;
	private String subclass_levels;
	private String url;
	
}
