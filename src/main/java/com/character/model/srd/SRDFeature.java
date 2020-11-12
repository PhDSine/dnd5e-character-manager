package com.character.model.srd;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SRDFeature {

	private String index;
	@JsonProperty("class")
	private APIReference playerClass;
	private APIReference subclass;
	private String name;
	private int level;
	private List<String> desc;
	private String url;
}
