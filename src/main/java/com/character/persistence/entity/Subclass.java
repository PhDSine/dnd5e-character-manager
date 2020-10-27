package com.character.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Subclass extends BaseEntity {

	@ManyToOne
	@JoinColumn(name="parentClassId", nullable=false)
    @ToString.Exclude
	protected ClassEntity parentClass;
	
	protected String name;
	protected String description;
}
