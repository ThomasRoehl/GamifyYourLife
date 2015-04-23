package de.tro.development.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "navigationController")
@ViewScoped
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

}
