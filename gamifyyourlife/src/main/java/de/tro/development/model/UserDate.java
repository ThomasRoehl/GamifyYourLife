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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserDate implements Serializable{
	
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
	@JoinColumn(name = "category_fk", nullable = false)
	private Category category;
	
	@Column(name = "points", updatable = false, nullable = false)
	private Long points;
	
	@Column(name = "begin_time", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date begin_time;
	
	@Column(name = "end_time", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date end_time;
	
	@Column(name = "day_event")
	private boolean day_event;

	public UserDate() {
		super();
	}

	public UserDate(String name, String description, Category category,
			Long points, Date begin_time, Date end_time, boolean day_event) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.points = points;
		this.begin_time = begin_time;
		this.end_time = end_time;
		this.day_event = day_event;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public boolean isDay_event() {
		return day_event;
	}

	public void setDay_event(boolean day_event) {
		this.day_event = day_event;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Dates [id=" + id + ", name=" + name + ", description="
				+ description + ", category=" + category + ", points=" + points
				+ ", begin_time=" + begin_time + ", end_time=" + end_time
				+ ", day_event=" + day_event + "]";
	}
}
