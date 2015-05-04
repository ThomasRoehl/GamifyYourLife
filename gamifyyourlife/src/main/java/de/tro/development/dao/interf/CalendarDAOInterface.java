package de.tro.development.dao.interf;

import java.util.Set;

import de.tro.development.model.UserDate;

public interface CalendarDAOInterface {
	
	public boolean createNewDate(UserDate date, int id);
	public Set<UserDate> getUserDatesByID(int id);

}
