package de.tro.development.dao.impl;

public abstract class DaoFactory {
	
	public static UserDAO getUserDAO(){
		return new UserDAO();
	}
	
	public static TaskDAO getTaskDAO(){
		return new TaskDAO();
	}
	
	public static CalendarDAO getCalendarDAO(){
		return new CalendarDAO();
	}
	
}
