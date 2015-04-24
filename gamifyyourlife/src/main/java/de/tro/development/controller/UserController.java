package de.tro.development.controller;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import de.tro.development.model.UserProfile;
import de.tro.development.service.UserSession;

@ManagedBean(name ="userController")
@SessionScoped
public class UserController {
	
	private Integer user_id;
	
	private String username;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String mail;
	
	private String street;
	
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	
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

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String logout(){
		clearData();
		clearLogin();
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
			clearData();
			return "index?faces-redirect=true";
		}
		return "register?faces-redirect=true";
	}
	
	public boolean checkUser(String username, String password){
		try {
			Query query = em.createNamedQuery("UserProfile.checkUserLogin");
			query.setParameter("username", username);
			query.setParameter("password", password);
			if (!(query.getResultList().get(0).equals(new Long(0)))) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String login(){
		
		if (checkUser(this.username, this.password)){
			try {
				Query query = em.createNamedQuery("UserProfile.findUserByName");
				query.setParameter("username", username);
				setUser_id((Integer) query.getResultList().get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
			userSession.setUser_id(this.user_id);
			userSession.setUsername(this.username);
			return "home"; 
		}
		return "index";
	}
	
	public void clearData(){
		this.firstname = "";
		this.lastname = "";
		this.mail = "";
		this.street = "";
	}
	
	public void clearLogin(){
		this.username = "";
		this.password = "";
		userSession.setUser_id(-1);
		userSession.setUsername("");
	}
}
