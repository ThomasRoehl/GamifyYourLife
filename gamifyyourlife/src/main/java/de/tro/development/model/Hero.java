package de.tro.development.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hero {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name", updatable = false, nullable = false)
	private String name;
	
	@Column(name = "description", updatable = false, nullable = false)
	private String description;
	
	@Column(name = "level", nullable = false)
	private Long level;
}
