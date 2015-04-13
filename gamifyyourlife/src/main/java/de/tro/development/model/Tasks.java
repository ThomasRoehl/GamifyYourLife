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
public class Tasks implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_fk", nullable = false)
	private Category category;
	
	@Column(name = "name", updatable = true, nullable = false)
	private String name;
	
	@Column(name = "description", updatable = true)
	private String description;
	
	@Column(name = "points", updatable = false, nullable = false)
	private Long points;
	
	@Column(name = "settlement_date", updatable = true)
	private java.util.Date settlement_date;
	
	public Tasks() {
		super();
	}

	public Tasks(Category category, String name, String description,
			Long points, Date settlement_date) {
		super();
		this.category = category;
		this.name = name;
		this.description = description;
		this.points = points;
		this.settlement_date = settlement_date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public java.util.Date getSettlement_date() {
		return settlement_date;
	}

	public void setSettlement_date(java.util.Date settlement_date) {
		this.settlement_date = settlement_date;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Tasks [id=" + id + ", category=" + category + ", name=" + name
				+ ", description=" + description + ", points=" + points
				+ ", settlement_date=" + settlement_date + "]";
	}
}
