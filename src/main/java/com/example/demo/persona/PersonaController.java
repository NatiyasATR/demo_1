package com.example.demo.persona;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import lombok.ToString;

@RestController
class PersonaService{
	
	@Autowired
	PersonaRepository repository;
	
	@PostMapping("/persona")
	public Persona createPersona(@RequestBody Persona persona) throws JSONException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//Persona p = new Persona();
		//p.setName(name);
		System.out.println(persona);
		System.out.println(persona.getAmigos());
		System.out.println(persona.getPadre());
		//MappingJackson2JsonView a = new MappingJackson2JsonView();
		return repository.save(persona);
	}
	
	@GetMapping("/persona")
	public Persona getpersona(@RequestParam(value = "id") int id) throws JSONException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return repository.findById(id).get();
	}
	
	
	@GetMapping("/personas")
	public List<Persona> getPersonas() throws JSONException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return repository.findAll();
	}
	
	@GetMapping("/cosas")
	public void cosas() throws JSONException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Persona a = repository.findById(1).get();
	System.out.println(a);
	System.out.println(a.getPadre());
		
	}
	
}