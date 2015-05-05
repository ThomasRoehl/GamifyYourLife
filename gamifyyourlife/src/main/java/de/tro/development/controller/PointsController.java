package de.tro.development.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import de.tro.development.dao.impl.UserDAO;
import de.tro.development.service.UserSession;

@ManagedBean
@RequestScoped
public class PointsController {

	private Long points;
	
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	
	@ManagedProperty(value = "#{userDAO}")
	private UserDAO userDAO;
	
	/**
	 * load user points from db
	 */
	@PostConstruct
	public void init(){
		points = userDAO.getUserPoints(userSession.getUser_id());
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}
	
	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
}
