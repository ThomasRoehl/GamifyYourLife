package de.tro.development.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hero implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name", updatable = false, nullable = false)
	private String name;
	
	@Column(name = "description", updatable = false, nullable = false)
	private String description;
	
	@Column(name = "level", nullable = false)
	private Long level;

	public Hero() {
		super();
	}

	public Hero(String name, String description, Long level) {
		super();
		this.name = name;
		this.description = description;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", description="
				+ description + ", level=" + level + "]";
	}
}
