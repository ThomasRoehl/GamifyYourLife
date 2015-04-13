package de.tro.development.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name", updatable = false, nullable = false)
	private String name;
	
	@Column(name = "zip", updatable = false, nullable = false)
	private String zip;

	public City() {
		super();
	}

	public City(String name, String zip) {
		super();
		this.name = name;
		this.zip = zip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", zip=" + zip + "]";
	}
}
