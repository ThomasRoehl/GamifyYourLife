package de.tro.development.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.tro.development.model.Task;
import de.tro.development.service.UserSession;

@ManagedBean(name="taskController")
@ApplicationScoped
public class TaskController {

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

	public List<Task> getTasks() {
		TypedQuery<Task> query = em.createNamedQuery("", Task.class);
		query.setParameter("user_id", UserSession.user_id);
		return query.getResultList();
	}	
}
