package com.character.persistence.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@JsonIgnore 
	@Version 
	protected Integer version;
	
	@CreationTimestamp
	protected Instant creationTs;
	
	@UpdateTimestamp 
	protected Instant updatedTs;
	
	protected String createdBy = "admin";
	
	protected String updatedBy = "admin";
	
	
}
