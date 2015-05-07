package de.tro.development.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.tro.development.dao.impl.UserDAO;
import de.tro.development.model.Todo_list;
import de.tro.development.model.UserProfile;
import de.tro.development.service.UserSession;

/**
 * @author TRO
 * Manage all user requests
 */
@ManagedBean(name ="userController")
@RequestScoped
public class UserController {
	
	private Integer user_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String mail;
	private String street;
	private boolean foundUser = false;
	private String foundMessage = "";

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	
	@ManagedProperty(value = "#{userDAO}")
	private UserDAO userDAO;
	
	@ManagedProperty(value = "#{taskController}")
	private TaskController taskController;
	
	// GETTER SETTER
	
	public boolean isFoundUser() {
		return foundUser;
	}

	public void setFoundUser(boolean foundUser) {
		this.foundUser = foundUser;
	}
	
	public String getUsername() {
		return username;
	}

	public TaskController getTaskController() {
		return taskController;
	}

	public void setTaskController(TaskController taskController) {
		this.taskController = taskController;
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
	
	public NavigationController getNavi(){
		return navi;
	}
	
	public void setNavi(NavigationController navi){
		this.navi = navi;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public String getFoundMessage() {
		return foundMessage;
	}

	public void setFoundMessage(String foundMessage) {
		this.foundMessage = foundMessage;
	}

	// FUNCTIONS
	
	/**
	 * Logout user and clear all data
	 * @return target page
	 */
	public String logout(){
		clearData(1);
		clearLogin();
		return navi.moveToIndex();
	}
	
	/**
	 * create new user
	 * @return true if new user added to DB else false
	 */
	public boolean createUser(){
		try {
			UserProfile user = new UserProfile();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setMail(mail);
			user.setPassword(password);
			user.setUsername(username);
			user.setStreet1(street);
			user.setPoints(0L);
			user.setHero_level(0);
			Todo_list tl = new Todo_list();
			user.setTodo_list(tl);
			return userDAO.createUser(user);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * move back to startpage if user was created successfully
	 * @return
	 */
	public String registerUser(){
		if (createUser()){
			clearData(0);
			return navi.moveToIndex();
		}
		return null;
	}
	
	/**
	 * login user and set username, user_id and todo_list_id in SessionBean (userSession) if success
	 * @return
	 */
	public String login(){
		
		if (userDAO.login(this.username, this.password)){
			userSession.setUser_id(userDAO.findUserID(this.username));
			userSession.setUsername(this.username);
			userSession.setTodo_list_id(userDAO.findTodo_list(username));
			clearData(1);
			return navi.moveToHome();
		}
		
		return null;
	}
	
	/**
	 * clear all data from register user
	 */
	public void clearData(int i){
		this.firstname = "";
		this.lastname = "";
		this.mail = "";
		this.street = "";
		if (i == 1){
			this.username = "";
			this.password = "";
		}
	}
	
	/**
	 * clear login data (also in SessionBean)
	 */
	public void clearLogin(){
		this.username = "";
		this.password = "";
		userSession.setUser_id(-1);
		userSession.setUsername("");
	}
	
	/**
	 * look for user in db by username
	 * if success set firstname and lastname of found user 
	 */
	public String findUser(){
		List<String> l = userDAO.findUserProfileByName(username);
		if (l == null){
			foundMessage = "user not found";
			this.foundUser = false;
			clearData(1);
		}
		else{
			foundMessage = "";
			this.foundUser = true;
			this.firstname = l.get(0);
			this.lastname = l.get(1);
		}
		return null;
	}
	
	/**
	 * add user to contacts, if no entry exists
	 * @return move to contacts page if success, else null
	 */
	public String addUser(){
		clearData(1);
		return navi.moveToContacts();
	}
}
