package com.character.model.srd;

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
public class SRDProficiency {

	private String index;
	private String type;
	private String name;
	private String url;

	private List<APIReference> classes;
	private List<APIReference> races;
	private List<APIReference> references;
}
