package com.example.demo.languaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageControler {
	
	@Autowired
	LanguageRepository repository;
	
	@GetMapping("/language")
	public Language language(@RequestParam(value = "id") int id) {
		return repository.findById(id).get();
	}
	
	@PostMapping("/language")
	public Language createLanguage(@RequestBody Language language) {
		return repository.save(language);
	}
	
	
}