package de.tro.development.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name", updatable = false, nullable = false)
	private String name;
	
	@Column(name = "continent", updatable = false, nullable = false)
	private String continent;

	public Country() {
		super();
	}

	public Country(String name, String continent) {
		super();
		this.name = name;
		this.continent = continent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", continent="
				+ continent + "]";
	}
}
