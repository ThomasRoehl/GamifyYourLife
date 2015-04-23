package de.tro.development.controller;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import de.tro.development.model.UserProfile;
import de.tro.development.service.UserSession;

@ManagedBean(name="userController")
@ApplicationScoped
public class UserController {
	
	private Integer user_id;
	
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

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String logout(){
		setPassword("");
		setUsername("");
		setUser_id(-1);
		UserSession.user_id = -1;
		return "index";
	}
	
	public boolean createUser(){
		try {
			System.out.println("-------createUser----------");
			UserProfile user = new UserProfile();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setMail(mail);
			user.setPassword(password);
			user.setUsername(username);
			user.setStreet1(street);
			user.setPoints(0L);
			
			utx.begin();
			em.persist(user);
			utx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String registerUser(){
		if (createUser()){
			return "home";
		}
		return "register";
	}
	
	public boolean checkUser(String username, String password){
		Query query = em.createNamedQuery("Users.checkUserLogin");
		query.setParameter("username", username);
		query.setParameter("password", password);
		if (!(query.getResultList().get(0).equals(new Long(0)))) return true;
		return false;
	}

	public String login(){
		
		System.out.println("---------- " + password + "  --  " + username + " ------------ ");
		if (checkUser(this.username, this.password)){
			Query query = em.createNamedQuery("Users.findUserByName");
			query.setParameter("username", username);
			setUser_id((Integer) query.getResultList().get(0));
			UserSession.user_id = this.user_id;
			return "home"; 
		}
		return "index";
	}
}
