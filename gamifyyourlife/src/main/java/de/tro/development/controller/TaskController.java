package de.tro.development.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.tro.development.model.Task;
import de.tro.development.model.Todo_list;
import de.tro.development.service.UserSession;

@ManagedBean(name="taskController")
@SessionScoped
public class TaskController {

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	
	@PersistenceContext( unitName="gamifyyourlife")
	protected  EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	private Long userID;
	
	public Long getUserID() {
		return userID;
	}
	
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<Task> getTasks() {
		List<Task> resultList = new ArrayList<Task>();
		try {
			TypedQuery<Todo_list> query = em.createNamedQuery("Todo_list.findAllTasksByID", Todo_list.class);
			query.setParameter("id", userSession.getUser_id());
			if (!query.getResultList().isEmpty()){
				resultList.addAll(query.getResultList().get(0).getTasks());
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}	
}
