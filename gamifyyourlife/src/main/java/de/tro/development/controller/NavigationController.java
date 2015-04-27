package de.tro.development.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author TRO
 *
 * Move to different jsf pages
 */
@ManagedBean(name = "navigationController")
@SessionScoped
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

}
