package de.tro.development.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

import de.tro.development.dao.impl.CalendarDAO;
import de.tro.development.dao.impl.CategoryDAO;
import de.tro.development.model.Category;
import de.tro.development.model.UserDate;
import de.tro.development.service.UserSession;

@ManagedBean(name = "calendarController")
@ViewScoped
public class CalendarController implements Serializable {

	/**
	 * vars ss
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Long points;
	private Date dateBegin;
	private Date dateEnd;
	private String category;
	private boolean dayEvent;
	private String DATEPATTERN = "MM/dd/yyyy HH:mm";
	private ScheduleModel lazyEventModel;
	private String error = "";
	private List<String> categories = new ArrayList<String>();

	@ManagedProperty(value = "#{calendarDAO}")
	private CalendarDAO calendarDAO;
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	@ManagedProperty(value = "#{categoryDAO}")
	private CategoryDAO categoryDAO;

	/**
	 * load UserDates from db into calendar
	 */
	@PostConstruct
	public void init() {
		setLazyEventModel(new LazyScheduleModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("deprecation")
			@Override
			public void loadEvents(Date start, Date end) {
				Set<UserDate> dates = getUserDates();
				for (UserDate d : dates) {
					if (d.isDay_event()) {
						Date day = d.getBegin_time();
						day.setDate(d.getBegin_time().getDate() + 1);
						addEvent(new DefaultScheduleEvent(d.getName(), day,
								day, d.isDay_event()));
					} else {
						Date day = d.getBegin_time();
						day.setHours(d.getBegin_time().getHours() + 1);
						Date endtime = d.getEnd_time();
						endtime.setHours(d.getEnd_time().getHours() + 1);
						addEvent(new DefaultScheduleEvent(d.getName(), day,
								endtime, d.isDay_event()));
					}
				}
			}
		});
	}

	// GETTER SETTER
	
	public List<String> getCategories() {
		categories = categoryDAO.getCategories();
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

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

	/**
	 * change pattern if dayevent is true
	 * 
	 * @param dayEvent
	 */
	public void setDayEvent(boolean dayEvent) {
		if (dayEvent)
			setDATEPATTERN("MM/dd/yyyy");
		else
			setDATEPATTERN("MM/dd/yyyy HH:mm");
		this.dayEvent = dayEvent;
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

	/**
	 * db request for UserDates
	 * 
	 * @return
	 */
	protected Set<UserDate> getUserDates() {
		return calendarDAO.getUserDatesByID(userSession.getTodo_list_id());
	}

	/**
	 * create entry in db
	 * 
	 * @param UserDate
	 *            date
	 * @return true if success else false
	 */
	protected boolean createDate(UserDate date) {
		try {
			return calendarDAO.createNewDate(date,
					userSession.getTodo_list_id());

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * init UserDate creating. if success link to calendar page
	 * 
	 * @return calendar if success, else null
	 */
	public String showNewDate() {
		error = "wrong input";
		UserDate date;
		if (!dayEvent && dateEnd == null){
			error = "please choose end date";
			return null;
		}
		if (dateBegin == null){
			error = "please choose begin date";
			return null;
		}
		if (!dayEvent && dateBegin.after(dateEnd)){
			error = "begin date can not be after end date";
			return null;
		}
			
		if (!dayEvent) {
			date = new UserDate(name, description, categoryDAO.getCategoryByName(category),
					points, dateBegin, dateEnd, dayEvent);
		} else {
			date = new UserDate(name, description, new Category(category, ""),
					points, dateBegin, dateBegin, dayEvent);
		}

		if (createDate(date)) {
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
