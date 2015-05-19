package de.tro.development.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer user_id;
	private String username;
	private Integer todo_list_id;
	private String heroName;

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public Integer getTodo_list_id() {
		return todo_list_id;
	}

	public void setTodo_list_id(Integer todo_list_id) {
		this.todo_list_id = todo_list_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
