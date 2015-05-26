package de.tro.development.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.tro.development.dao.impl.MessageDAO;
import de.tro.development.dao.impl.TaskDAO;
import de.tro.development.dao.impl.UserDAO;
import de.tro.development.model.Message;
import de.tro.development.model.Task;
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

	private String sendObject;

	private boolean systemTask;
	private String taskName;
	private String taskDescription;
	private String taskPoints;

	public final String TASKSYSTEMMESSAGE = " has send you a task: ";
	public final String TASKREJECTMESSAGE = " has rejected your task: ";
	public final String TASKACCEPTMESSAGE = " has accepted your Task: ";

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	@ManagedProperty(value = "#{messageDAO}")
	private MessageDAO messageDAO;
	@ManagedProperty(value = "#{userDAO}")
	private UserDAO userDAO;
	@ManagedProperty(value = "#{taskDAO}")
	private TaskDAO taskDAO;

	// GETTER / SETTER

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskPoints() {
		return taskPoints;
	}

	public void setTaskPoints(String taskPoints) {
		this.taskPoints = taskPoints;
	}

	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public boolean isSystemTask() {
		return systemTask;
	}

	public void setSystemTask(boolean systemTask) {
		this.systemTask = systemTask;
	}

	public String getSendObject() {
		return sendObject;
	}

	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}

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

	public void showMsg(Message msg) {
		currentMsg = msg;
		if (currentMsg.getSendObject() != null && currentMsg.isSystem()){
			this.systemTask = true;
		}
		else
			this.systemTask = false;
		showTask();
	}
	
	public void sendSystem(String sendObject){
		Message msg = new Message();
		msg.setMsg(newMsg);
		msg.setRegard(newRegard);
		msg.setSendObject(sendObject);
		msg.setSystem(true);
		messageDAO.sendMessage(newUser, "System", msg);
		newMsg = "";
		newRegard = "";
		newUser = "";
	}

	public void send() {
		Message msg = new Message();
		msg.setMsg(newMsg);
		msg.setRegard(newRegard);
		messageDAO.sendMessage(newUser, userSession.getUsername(), msg);
		newMsg = "";
		newRegard = "";
		newUser = "";
	}

	public void acceptTask() {
		String taskname = currentMsg.getSendObject().substring(5);
		String sender = currentMsg.getMsg().split(" ")[0];
		taskDAO.changeTaskOwnerTo(sender, userSession.getTodo_list_id(),
				taskname);
		Message msg = new Message();
		msg.setMsg(userSession.getUsername() + this.TASKACCEPTMESSAGE
				+ taskname);
		msg.setRegard("Send task accepted");
		msg.setSystem(true);
		messageDAO.sendMessage(sender, "System", msg);
	}

	public void rejectTask() {
		String sender = currentMsg.getMsg().split(" ")[0];
		String taskname = currentMsg.getSendObject().substring(5);
		Message msg = new Message();
		msg.setMsg(userSession.getUsername() + this.TASKREJECTMESSAGE
				+ taskname);
		msg.setRegard("Send task rejected");
		msg.setSystem(true);
		messageDAO.sendMessage(sender, "System", msg);
	}

	public void showTask() {
		if (currentMsg.isSystem() && currentMsg.getSendObject() != null) {
			String taskname = currentMsg.getSendObject().substring(5);
			Task t = taskDAO.getTaskByName(taskname);
			this.taskDescription = t.getDescription();
			this.taskName = t.getName();
			this.taskPoints = t.getPoints().toString();
		}
	}
}
