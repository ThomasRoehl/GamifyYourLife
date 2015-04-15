package de.tro.development.controller;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ManagedBean(name="testController")
@ViewScoped
public class TestController {
	
	@PersistenceContext( unitName="gamifyyourlife")
	protected  EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	private String user;
	
	public TestController(){
		this.user = "ERROR";
	}
	
	public String getUser(){
		return this.user;
	}
	
	public void setUser(){
		this.user = "Test";
	}

}
