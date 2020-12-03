package com.character.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "class_spell_list")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClassSpell extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name="parentClassId", nullable=false)
    @ToString.Exclude
	protected ClassEntity parentClass;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="parentClass")
	protected List<Subclass> subclasses;
	
}
