package com.character.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.character.model.CharacterCreator;
import com.character.persistence.entity.ClassEntity;
import com.character.persistence.entity.Race;
import com.character.persistence.entity.Subclass;
import com.character.persistence.repository.ClassRepository;
import com.character.persistence.repository.RaceRepository;
import com.character.service.CharacterManagerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CharacterManagerController {

	@Autowired
	CharacterManagerService service;
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	RaceRepository raceRepository;
	
	@GetMapping({"/", "/home"})
	public String newCharacter(Model model) {
		model.addAttribute("newCharacter", new CharacterCreator());

		List<Race> races = raceRepository.findAll();
		model.addAttribute("races", races);
		
		List<ClassEntity> classes = classRepository.findAll();
		model.addAttribute("classes", classes);
		
		List<Subclass> subclasses = service.getAllSubclasses(classes);
		model.addAttribute("subclasses", subclasses);
		return "home";
	}
}
