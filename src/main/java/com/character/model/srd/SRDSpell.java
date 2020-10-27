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
public class SRDSpell implements Serializable {

	private static final long serialVersionUID = 1L;

	private String index;
	private String name;
	private String[] desc;
	private String[] higher_level;
	private String range;
	private String[] components;
	private String material;
	private boolean ritual;
	private String duration;
	private boolean concentration;
	private String casting_time;
	private String level;
	private String attack_type;
	private SRDDamage damage;
	private SRDNameUrl school;
	private SRDNameUrl[] classes;
	private SRDNameUrl[] subclasses;
}
