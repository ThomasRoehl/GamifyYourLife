package de.tro.development.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

import de.tro.development.dao.impl.CalendarDAO;
import de.tro.development.model.Category;
import de.tro.development.model.UserDate;
import de.tro.development.service.UserSession;

@ManagedBean(name = "calendarController")
@RequestScoped
public class CalendarController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Long points;
	private Date dateBegin;
	private Date dateEnd;
	// private Date timeBegin;
	// private Date timeEnd;
	private String category;
	private boolean dayEvent;
	private String DATEPATTERN = "MM/dd/yyyy HH:mm";
	private ScheduleModel lazyEventModel;

	@ManagedProperty(value = "#{calendarDAO}")
	private CalendarDAO calendarDAO;
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;

	@PostConstruct
	public void init() {
		setLazyEventModel(new LazyScheduleModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void loadEvents(Date start, Date end) {
				Set<UserDate> dates = calendarDAO.getUserDatesByID(userSession
						.getTodo_list_id());
				for (UserDate d : dates) {
					if (!d.isDay_event())
						addEvent(new DefaultScheduleEvent(d.getName(),
								d.getBegin_time(), d.getEnd_time()));
					else
						addEvent(new DefaultScheduleEvent(d.getName(),
								d.getBegin_time(), d.getBegin_time(),
								d.isDay_event()));
				}
			}
		});
	}

	// GETTER SETTER

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public NavigationController getNavi() {
		return navi;
	}

	public void setNavi(NavigationController navi) {
		this.navi = navi;
	}

	public CalendarDAO getCalendarDAO() {
		return calendarDAO;
	}

	public void setCalendarDAO(CalendarDAO calendarDAO) {
		this.calendarDAO = calendarDAO;
	}

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
		if (dayEvent)
			setDATEPATTERN("MM/dd/yyyy");
		else
			setDATEPATTERN("MM/dd/yyyy HH:mm");
		this.dayEvent = dayEvent;
		System.out.println("PATTERN = " + this.DATEPATTERN);
	}

	/*
	 * public Date getTimeBegin() { return timeBegin; } public void
	 * setTimeBegin(Date timeBegin) { this.timeBegin = timeBegin; } public Date
	 * getTimeEnd() { return timeEnd; } public void setTimeEnd(Date timeEnd) {
	 * this.timeEnd = timeEnd; }
	 */

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

	// FUNCTIONS

	public String showNewDate() {
		System.out.println("------test " + this.toString());
		System.out.println("Begin: " + dateBegin);
		System.out.println("End: " + dateEnd);
		UserDate date;
		if (!dayEvent) {
			date = new UserDate(name, description, new Category(
					category, ""), points, dateBegin, dateEnd, dayEvent);
		} else {
			date = new UserDate(name, description, new Category(
					category, ""), points, dateBegin, dateBegin, dayEvent);
		}
		if (calendarDAO.createNewDate(date, userSession.getUser_id())) {
			return navi.moveToCalendar();
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "CalendarController [name=" + name + ", description="
				+ description + ", points=" + points + ", begin=" + dateBegin
				+ ", end=" + dateEnd + ", category=" + category + ", dayEvent="
				+ dayEvent + "]";
	}
}
