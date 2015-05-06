package de.tro.development.dao.impl;

import de.tro.development.dao.interf.CalendarDAOInterface;
import de.tro.development.dao.interf.TaskDAOInterface;
import de.tro.development.dao.interf.UserDAOInterface;

public abstract class DaoFactory {
	
	public static UserDAOInterface getUserDAO(){
		return new UserDAO();
	}
	
	public static TaskDAOInterface getTaskDAO(){
		return new TaskDAO();
	}
	
	public static CalendarDAOInterface getCalendarDAO(){
		return new CalendarDAO();
	}
	
}
