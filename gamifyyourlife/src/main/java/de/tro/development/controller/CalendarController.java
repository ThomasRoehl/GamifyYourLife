package de.tro.development.controller;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.taglibs.standard.tag.el.sql.SetDataSourceTag;

@ManagedBean(name="calendarController")
@RequestScoped
public class CalendarController {
	
	private String name;
	private String description;
	private Long points;
	private Date dateBegin;
	private Date dateEnd;
	private Date timeBegin;
	private Date timeEnd;
	private String category;
	private boolean dayEvent;
	private String DATEPATTERN = "MM/dd/yyyy HH:mm";
	
	// GETTER SETTER
	
	public String getDATEPATTERN() {
		return DATEPATTERN;
	}
	public void setDATEPATTERN(String dATEPATTERN) {
		DATEPATTERN = dATEPATTERN;
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
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date begin) {
		this.dateBegin = begin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date end) {
		this.dateEnd = end;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isDayEvent() {
		return dayEvent;
	}
	public void setDayEvent(boolean dayEvent) {
		if (dayEvent) setDATEPATTERN("MM/dd/yyyy");
		else setDATEPATTERN("MM/dd/yyyy HH:mm");
		this.dayEvent = dayEvent;
	}
	public Date getTimeBegin() {
		return timeBegin;
	}
	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}
	public Date getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	// FUNCTIONS
	
	public String showNewDate(){
		System.out.println("------test " + this.toString());
		return null;
	}
	
	@Override
	public String toString() {
		return "CalendarController [name=" + name + ", description="
				+ description + ", points=" + points + ", begin=" + dateBegin
				+ ", end=" + dateEnd + ", category=" + category + ", dayEvent="
				+ dayEvent + "]";
	}
}
