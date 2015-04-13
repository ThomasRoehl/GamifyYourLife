package de.tro.development.model;

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
public class Dates {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_fk", nullable = false)
	private Category category;
	
	@Column(name = "points", updatable = false, nullable = false)
	private Long points;
	
	@Column(name = "begin_time", nullable = false)
	private Date begin_time;
	
	@Column(name = "end_time", nullable = false)
	private Date end_time;
	
	@Column(name = "day_event")
	private boolean day_event;
}
