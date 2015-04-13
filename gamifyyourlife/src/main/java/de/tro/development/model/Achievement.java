package de.tro.development.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Achievement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "points_needed", updatable = false, nullable = false)
	private Long points_needed;

	public Achievement() {
		super();
	}
	
	public Achievement(String name, String description, Long points_needed) {
		super();
		this.name = name;
		this.description = description;
		this.points_needed = points_needed;
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

	public Long getPoints_needed() {
		return points_needed;
	}

	public void setPoints_needed(Long points_needed) {
		this.points_needed = points_needed;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Achievement [id=" + id + ", name=" + name + ", description="
				+ description + ", points_needed=" + points_needed + "]";
	}
}
