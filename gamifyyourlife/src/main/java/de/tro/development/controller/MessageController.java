package de.tro.development.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.tro.development.dao.impl.MessageDAO;
import de.tro.development.dao.impl.UserDAO;
import de.tro.development.model.Message;
import de.tro.development.service.UserSession;

@ManagedBean
@ViewScoped
public class MessageController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Message> messages = new ArrayList<Message>();
	private Message currentMsg;
	private String newMsg;
	private String newRegard;
	private String newUser;
	
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	@ManagedProperty(value = "#{messageDAO}")
	private MessageDAO messageDAO;
	@ManagedProperty(value = "#{userDAO}")
	private UserDAO userDAO;
	
	// GETTER / SETTER
	
	public List<Message> getMessages() {
		
		messages = messageDAO.getUserMessages(userSession.getUser_id());
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Message getCurrentMsg() {
		return currentMsg;
	}
	public void setCurrentMsg(Message currentMsg) {
		this.currentMsg = currentMsg;
	}
	public String getNewMsg() {
		return newMsg;
	}
	public void setNewMsg(String newMsg) {
		this.newMsg = newMsg;
	}
	public String getNewRegard() {
		return newRegard;
	}
	public void setNewRegard(String newRegard) {
		this.newRegard = newRegard;
	}
	public String getNewUser() {
		return newUser;
	}
	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}
	public UserSession getUserSession() {
		return userSession;
	}
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	public NavigationController getNavi() {
		return navi;
	}
	public void setNavi(NavigationController navi) {
		this.navi = navi;
	}
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void showMsg(Message msg){
		System.out.println("test");
		currentMsg = msg;
	}
	
	public void send(){
		Message msg = new Message();
		msg.setMsg(newMsg);
		msg.setRegard(newRegard);
		messageDAO.sendMessage(newUser, userSession.getUsername(), msg);
	}
}
