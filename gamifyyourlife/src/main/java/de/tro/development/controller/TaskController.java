package de.tro.development.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import de.tro.development.dao.impl.CategoryDAO;
import de.tro.development.dao.impl.TaskDAO;
import de.tro.development.model.Category;
import de.tro.development.model.Task;
import de.tro.development.service.UserSession;

/**
 * @author TRO
 * Manage all Task requests
 */
@ManagedBean(name = "taskController")
@ViewScoped
public class TaskController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	
	@ManagedProperty(value = "#{taskDAO}")
	private TaskDAO taskDAO;
	
	@ManagedProperty(value = "#{categoryDAO}")
	private CategoryDAO categoryDAO;

	private List<Task> tasks = new ArrayList<Task>();
	private String taskName;
	private String taskCategory;
	private String taskSettlement_date;
	private Long taskPoints;
	private Long userID;
	private List<String> categories = new ArrayList<String>();
	
	// GETTER SETTER
	
	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	public List<String> getCategories() {
		categories = categoryDAO.getCategories();
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public Long getTaskPoints() {
		return taskPoints;
	}

	public void setTaskPoints(Long taskPoints) {
		this.taskPoints = taskPoints;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String name) {
		this.taskName = name;
	}

	public String getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(String category) {
		this.taskCategory = category;
	}

	public String getTaskSettlement_date() {
		return taskSettlement_date;
	}

	public void setTaskSettlement_date(String settlement_date) {
		this.taskSettlement_date = settlement_date;
	}

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
	
	public NavigationController getNavi() {
		return navi;
	}

	public void setNavi(NavigationController navi) {
		this.navi = navi;
	}

	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	// FUNCTIONS
	
	/**
	 * update task list with DB data
	 */
	private void updateTasks(){
		Set<Task> res = taskDAO.updateTask(userSession.getTodo_list_id());
		if (res != null){
			tasks.clear();
			tasks.addAll(res);
		}
	}
	
	/**
	 * update and return task list 
	 * @return
	 */
	public List<Task> getTasks() {
		updateTasks();
		return tasks;
	}

	/**
	 * create new Task and try to insert into users DB
	 * @return
	 */
	public String createTask(){
		Task t = new Task();
		
		try {
			t.setCategory(categoryDAO.getCategoryByName(taskCategory));
			t.setName(taskName);
			t.setPoints(taskPoints);
			//t.setSettlement_date(new Date(taskSettlement_date));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		if (insertTask(t)) return navi.moveToHome();
		
		return null;

	}
	
	/**
	 * insert Task into DB
	 * user SessionBean.todo_list_id to find target user
	 * @param Task t to insert to current users list
	 * @return true if successfully added to DB else false
	 */
	protected boolean insertTask(Task t){
		try {
			return taskDAO.createTask(t, userSession.getTodo_list_id());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
