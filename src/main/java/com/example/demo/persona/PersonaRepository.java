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
import java.util.List;
import java.util.Optional;

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
	
	
	/*private Persona parse(JSONObject jo) throws JSONException {
		Persona p = new Persona();
		if(jo.has("name"))
			p.setName(jo.getString("name"));
		if(jo.has("id"))
			p.setName(jo.getString("id"));
				
		return p;
	}*/
	
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

	
	public Persona save(Persona persona) throws IOException, JSONException{
		List<Persona> personas = readFile();
		persona.setId(personas.size());
		personas.add(persona.getId(),persona);
		writeFile(personas);
		
		personas = readFile();
		
		return personas.get(persona.getId());
	}
	
	public String getEntityName() {
		return "Persona";
	}

	public <S extends Persona> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Persona> findById(int id) throws IOException, JSONException {
		List<Persona> personas = readFile();
		return personas.stream().filter(p->p.getId()==id).findFirst();
	}
  
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Persona> findAll() throws IllegalAccessException, InvocationTargetException, JSONException, IOException {
		return readFile();
	}

	public Iterable<Persona> findAllById(Iterable<String> ids) {
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
