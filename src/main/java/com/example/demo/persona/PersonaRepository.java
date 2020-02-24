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

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;

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
	
	private JSONArray readFile() throws IOException, JSONException {
		//FileInputStream fisTargetFile = new FileInputStream(new File(getEntityName()));
		//BufferedReader br = Files.newBufferedReader(Paths.get(getEntityName()));
		File f = new File(getEntityName()+".json");
		if(!f.exists()){
		  f.createNewFile();
		  BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		  bw.write("[]");
		  bw.close();
		  return new JSONArray("[]");
		}else {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String data = br.lines().reduce("", (s,l)->{return s+=l;});
			br.close();
			return new JSONArray(data);
		}
	}
	
	private void writeFile(JSONArray list) throws IOException, JSONException {
		File f = new File(getEntityName()+".json");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(list.toString(4));
		bw.close();
	}
	
	public JSONObject toJSON(Persona persona) throws JSONException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		JSONObject jsono = new JSONObject();
		for(Field f : Persona.class.getDeclaredFields()) {
			System.out.println(f.getType().getCanonicalName());
			switch(f.getType().getCanonicalName()) {
				case "int":
				case "java.lang.String":
					jsono.put(f.getName(),f.get(persona));	
					break;
			}
				
		}
		return jsono;
	}
	
	public Persona fromJSON(JSONObject jsono) throws IllegalAccessException, InvocationTargetException, JSONException {
		Persona persona = new Persona();
		for(Field f : Persona.class.getDeclaredFields()) {
			System.out.println(f.getType().getCanonicalName());
			switch(f.getType().getCanonicalName()) {
				case "int":
					f.setInt(persona, jsono.getInt(f.getName()));
					break;
				case "java.lang.String":
					f.set(persona, jsono.getString(f.getName()));
					break;
			}
				
		}
		return persona;
	}

	
	public Persona save(Persona persona) throws JSONException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		/*JSONArray list = readFile();
		persona.setId(list.length()+1);
		list.put(toJSON(persona));
		writeFile(list);*/
		
		System.out.println(persona);
		String json = new ObjectMapper().writeValueAsString(persona);
		System.out.println(json);
		Persona p = new ObjectMapper()
			      .readerFor(Persona.class)
			      .readValue(json);
		System.out.println(p);
		
		return persona;
	}
	
	public String getEntityName() {
		return "Persona";
	}

	public <S extends Persona> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Persona> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Persona> findAll() throws IllegalAccessException, InvocationTargetException, JSONException, IOException {
		ArrayList<Persona> result = new ArrayList<Persona>();
		JSONArray list = readFile();
		for (int i=0;i<list.length();i++) {
			result.add(fromJSON(list.getJSONObject(i))) ;
		}
		return result;
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
