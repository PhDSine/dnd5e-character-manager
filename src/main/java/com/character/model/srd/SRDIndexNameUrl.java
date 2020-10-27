package com.character.model.srd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SRDIndexNameUrl {

	private String index;
	private String name;
	private String url;
	
}
