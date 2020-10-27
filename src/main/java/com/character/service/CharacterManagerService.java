package com.character.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.character.persistence.entity.ClassEntity;
import com.character.persistence.entity.Subclass;
import com.character.persistence.repository.ClassRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CharacterManagerService {
	
	@Autowired
	ClassRepository classRepository;
	
	@Transactional
	public List<ClassEntity> getAllClasses(){
		return classRepository.findAll();
	}

	public List<Subclass> getAllSubclasses(List<ClassEntity> classes) {
		List<Subclass> subclasses = new ArrayList<>();
		for(ClassEntity parentClass : classes) {
			subclasses.addAll(parentClass.getSubclasses());
		}
		return subclasses;
	}
}
