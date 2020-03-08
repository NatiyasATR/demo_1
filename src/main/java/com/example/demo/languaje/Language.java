package com.example.demo.languaje;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table
@ToString
@AllArgsConstructor
//@NoArgsConstructor
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column
	String name;
	
	@OneToMany(mappedBy = "padre")
	List<Language> hijos;
	
	@ManyToOne
	public Language padre;
	
}
