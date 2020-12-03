package com.character.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.character.model.CharacterCreator;
import com.character.persistence.entity.ClassEntity;
import com.character.persistence.entity.Race;
import com.character.persistence.entity.Spell;
import com.character.persistence.entity.Subclass;
import com.character.persistence.repository.ClassRepository;
import com.character.persistence.repository.SpellRepository;
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
	SpellRepository spellRepo;
	
	@GetMapping({"/", "/home"})
	public String homepage(Model model) {
		return "home";
	}
	
	@GetMapping("spells")
	public String spells(Model model) {
		List<Spell> spells = spellRepo.findByOrderBySpellName();
		model.addAttribute("spells", spells);
		return "spells";
	}
}
