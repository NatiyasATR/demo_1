package com.example.demo.persona;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class PersonaRepository{
	
	HashMap<Integer,Persona> list = new HashMap<Integer,Persona>();
	
	private List<Persona> readFile() throws IOException, JSONException {
		File f = new File(getEntityName()+".json");
		String data;
		if(!f.exists()){
		  f.createNewFile();
		  BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		  bw.write("[]");
		  bw.close();
		  data = "[]";
		}else {
			BufferedReader br = new BufferedReader(new FileReader(f));
			data = br.lines().reduce("", (s,l)->{return s+=l;});
			br.close();
		}
		return new ObjectMapper().readerFor(new TypeReference<List<Persona>>() {}).readValue(data);
	}
	
	private void writeFile(List<Persona> list) throws IOException, JSONException {
		File f = new File(getEntityName()+".json");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(new ObjectMapper().writeValueAsString(list));
		bw.close();
	}
	
	private void setRelations(Persona persona) throws IOException, JSONException {
		persona.setPadre(findById(persona.getPadre().getId()).orElse(null));
		persona.setAmigos(findAllById(persona.getAmigos().stream().map(p->p.getId()).collect(Collectors.toList())));
		System.out.println(persona.getAmigos());
	}

	
	public Persona save(Persona persona) throws IOException, JSONException{
		List<Persona> personas = readFile();
		persona.setId(personas.size());
		personas.add(persona.getId(),persona);
		writeFile(personas);
		personas = readFile();
		return findById(persona.getId()).get();
	}
	
	public String getEntityName() {
		return "Persona";
	}

	public <S extends Persona> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Persona> findById(int id) throws IOException, JSONException {
		Persona result = list.get(id);
		if(result!=null) return Optional.of(result);
		List<Persona> personas = readFile();
		Persona persona = personas.get(id);
		list.put(id, persona);
		setRelations(persona);
		return Optional.ofNullable(persona);
	}
  
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Persona> findAll() throws JSONException, IOException {
		List<Persona> personas = readFile();
		personas.forEach(p->{
			Persona persona = list.get(p.getId());
			if(persona==null) list.put(p.getId(), p);
		});
		return list.values().stream().collect(Collectors.toList());
	}

	public List<Persona> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Persona entity) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll(Iterable<? extends Persona> entities) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	

}
