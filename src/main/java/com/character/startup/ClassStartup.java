package com.character.startup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.character.persistence.entity.ClassEntity;
import com.character.persistence.repository.ClassRepository;
import com.character.service.SRDService;
import com.character.service.api.SRDSpellService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClassStartup  implements ApplicationRunner {
	
	@Autowired
	SRDService apiService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		apiService.testSomething();
	}
}
