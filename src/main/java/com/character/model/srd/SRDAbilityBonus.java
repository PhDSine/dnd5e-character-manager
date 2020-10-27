package com.character.model.srd;

import java.io.Serializable;

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
public class SRDAbilityBonus implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String index;
	private int bonus;
	private String url;
}
