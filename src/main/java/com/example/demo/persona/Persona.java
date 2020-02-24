package com.example.demo.persona;


import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//import javax.persistence.ManyToOne;
//import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

@Data
@Table
@ToString
public class Persona {
	
	@Id
	protected int id;
	
	@Column
	protected String name;
	
	@ManyToMany
	@ToString.Exclude
	protected List<Persona> amigos;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@ToString.Exclude
	private Map<String, String> properties;
	 
    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
    
    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }
	
	/*
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}*/
	
	
	
	
}
