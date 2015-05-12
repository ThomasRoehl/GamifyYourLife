package de.tro.development.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * @author TRO
 *
 * Move to different jsf pages
 */
@ManagedBean(name = "navigationController")
@ApplicationScoped
public class NavigationController {
	
	public String moveToHome(){
		return "home?faces-redirect=true";
	}
	
	public String moveToCalendar(){
		return "calendar?faces-redirect=true";
	}
	
	public String moveToRegister(){
		return "register?faces-redirect=true";
	}
	
	public String moveToNewTask(){
		return "task?faces-redirect=true";
	}
	
	public String moveToIndex(){
		return "index?faces-redirect=true";
	}
	
	public String moveToNewDate(){
		return "dates?faces-redirect=true";
	}
	
	public String moveToContacts(){
		return "contacts?faces-redirect=true";
	}
	
	public String moveToRewards(){
		return "rewards?faces-redirect=true";
	}
}
