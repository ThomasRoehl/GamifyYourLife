package de.tro.development.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import de.tro.development.dao.impl.UserDAO;
import de.tro.development.model.Todo_list;
import de.tro.development.model.UserProfile;
import de.tro.development.service.UserSession;

/**
 * @author TRO
 * Manage all user requests
 */
@ManagedBean(name ="userController")
@ViewScoped
public class UserController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer user_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String mail;
	private String street;
	private Long points;
	private String searchUsername;
	private boolean foundUser = false;
	private boolean inContacts = true;
	private String foundMessage = "";
	List<String> contacts = new ArrayList<String>();
	List<String> foundUserList = new ArrayList<String>();
	
	public UserController(){
		System.out.println("USER CONTROLLER BEAN CREATED");
	}
	
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	
	@ManagedProperty(value = "#{userDAO}")
	private UserDAO userDAO;
	
	@ManagedProperty(value = "#{taskController}")
	private TaskController taskController;
	
	// GETTER SETTER
	
	public String getSearchUsername() {
		return searchUsername;
	}

	public void setSearchUsername(String searchUsername) {
		System.out.println("searchUsername: " + searchUsername);
		this.searchUsername = searchUsername;
	}
	
	public List<String> getFoundUserList() {
		return foundUserList;
	}

	public void setFoundUserList(List<String> foundUserList) {
		this.foundUserList = foundUserList;
	}

	
	public boolean isInContact() {
		return inContacts;
	}

	public void setInContact(boolean isContact) {
		this.inContacts = isContact;
	}
	
	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}
	
	public List<String> getContacts() {
		updateContacts();
		return contacts;
	}

	public void setContacts(List<String> contacts) {
		this.contacts = contacts;
	}
	
	public boolean isFoundUser() {
		return foundUser;
	}

	public void setFoundUser(boolean foundUser) {
		this.foundUser = foundUser;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		System.out.println("username " + username);
		this.username = username;
	}

	public TaskController getTaskController() {
		return taskController;
	}

	public void setTaskController(TaskController taskController) {
		this.taskController = taskController;
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
	public void findUser(){
		System.out.println("[findUser] call " + username);
		List<String> l = userDAO.findUserDetailsByName(this.username);
		if (l == null){
			foundMessage = "user not found";
			this.foundUser = false;
			this.inContacts = true;
			clearData(1);
		}
		else{
			foundMessage = "";
			this.foundUser = true;
			this.firstname = l.get(0);
			this.lastname = l.get(1);
			this.mail = l.get(2);
			this.points = Long.parseLong(l.get(3));
			if (contacts.contains(this.username)) inContacts = true;
			else inContacts = false;
		}
		
	}
	
	/**
	 * show information of current user
	 */
	public void showUserProfile(){
		System.out.println("[showUserProfile]");
		findUser();
	}
	
	/**
	 * look for user with similar username
	 * @return
	 */
	public String findUserLike(){
		System.out.println("[findUserLike] " + this.searchUsername);
		this.foundUserList = userDAO.findUsersLikeName(this.searchUsername);
		return null;
	}
	
	/**
	 * add user to contacts, if no entry exists
	 * @return move to contacts page if success, else null
	 */
	public void addUser(){
		System.out.println("[addUser]");
		if (!this.contacts.contains(this.username) && !(this.username.equals(userSession.getUsername()))){
			if (userDAO.addContact(userSession.getUsername(), username)){
				updateContacts();
				clearData(1);
			}
		}
		else{
			System.out.println("can not add User");
		}
	}
	
	/**
	 * update contacts with db
	 */
	public void updateContacts(){
		System.out.println("[updateContacts]");
		contacts = userDAO.findContacts(userSession.getUsername());
		Collections.sort(contacts);
	}
}
