package de.tro.development.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rewards implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "achievement_fk", nullable = false)
	private Achievement achievement;

	public Rewards() {
		super();
	}

	public Rewards(String name, String description, Achievement achievement) {
		super();
		this.name = name;
		this.description = description;
		this.achievement = achievement;
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

	public Achievement getAchievement() {
		return achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Rewards [id=" + id + ", name=" + name + ", description="
				+ description + ", achievement=" + achievement + "]";
	}
}
