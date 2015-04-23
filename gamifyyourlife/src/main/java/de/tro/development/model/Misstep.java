package de.tro.development.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Misstep implements Serializable{
	
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
	
	@Column(name = "points_lost", nullable = false)
	private Long points_lost;

	public Misstep() {
		super();
	}

	public Misstep(String name, String description, Long points_lost) {
		super();
		this.name = name;
		this.description = description;
		this.points_lost = points_lost;
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

	public Long getPoints_lost() {
		return points_lost;
	}

	public void setPoints_lost(Long points_lost) {
		this.points_lost = points_lost;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Missteps [id=" + id + ", name=" + name + ", description="
				+ description + ", points_lost=" + points_lost + "]";
	}
}
