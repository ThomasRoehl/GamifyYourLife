package de.tro.development.controller;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

@ManagedBean(name = "navigationController")
@ViewScoped
public class NavigationController {
	
	private String username;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String mail;
	
	private String street;
	
	@PersistenceContext( unitName="gamifyyourlife")
	protected  EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String logout(){
		setPassword("");
		setUsername("");
		return "index";
	}
	
	public boolean createUser(){
		try {
			System.out.println("-------createUser----------");
			Query query = em.createQuery("INSERT INTO Users (firstname, lastname, username, password, mail, street1) VALUES (? ? ? ? ? ?)");
			query.setParameter("firstname", firstname);
			query.setParameter("lastname", lastname);
			query.setParameter("username", username);
			query.setParameter("password", password);
			query.setParameter("mail", mail);
			query.setParameter("street1", street);
			query.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public String registerUser(){
		createUser();
		return "home";
	}
	
	public boolean checkUser(){
		Query query = em.createNamedQuery("Users.checkUserLogin");
		query.setParameter("username", this.username);
		query.setParameter("password", this.password);
		if (!(query.getResultList().get(0).equals(new Long(0)))) return true;
		return false;
	}

	public String login(){
		if (checkUser()) return "home"; 
		return "index";
	}

}
