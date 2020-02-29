package com.example.demo.persona;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//import javax.persistence.ManyToOne;
//import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Data
@Table
@ToString
@AllArgsConstructor
//@NoArgsConstructor
public class Persona {
	
	//*TODO LAS OBJETOS VACIOS TAMBIEN SE GUARDAN EN ALLINSTANCES
	
	public Persona() {
		//allInstances.add( this );
	}
	
	public Persona(int id) {
		super();
		this.id = id;
		//allInstances.add( this );
	}
	/*
	private static HashSet<Persona> allInstances;
	  
    static
    {
        allInstances = new HashSet<Persona>();
    }
  
    public static synchronized HashSet<Persona> getAllInstances()
    {
        return (HashSet<Persona>)  allInstances.clone() ;
    }
  
    protected void finalize()
    {
        allInstances.remove( this );
    }*/


	@Id
	@JsonProperty
	private int id;
	
	@Column
	@JsonProperty
	private String name;
	
	@ManyToMany
	@ToString.Exclude
	//@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
	private List<Persona> amigos;
	
	@JsonGetter("amigos")
	public Object getJsonAmigos(){
		return amigos != null? amigos.stream().map(a->a.id).collect(Collectors.toList()): new ArrayList<Persona>();
	}
	
	
	@JsonSetter("amigos")
	public void setJsonAmigos(List<String> list){
		if(list == null) return;
		amigos = list.stream().map(a->new Persona(Integer.valueOf(a))).collect(Collectors.toList());
	}
	
	@OneToOne
	@ToString.Exclude
	private Persona padre;
	
	@JsonGetter("padre")
	public Object getJsonPadre() {
		return padre != null? padre.getId() : null;
	}
	
	
	@JsonSetter("padre")
	public void setJsonPadre(String p){
		if(p == null) return;
		padre = new Persona(Integer.valueOf(p));
	}
	
	

	
	
	
	
	
	
}
