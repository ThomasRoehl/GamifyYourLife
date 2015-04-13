package de.tro.development.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "profile_fk", nullable = false)
	private Profile profile;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "todo_list_fk", nullable = false)
	private Todo_list todo_list;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_achievement_fk", nullable = false)
	private User_Achievement user_achievement;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "hero_fk", nullable = false)
	private Hero hero;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "missteps_fk")
	private Set<Missteps> missteps = new HashSet<Missteps>();
	
	@Column(name = "points", nullable = false)
	private Long points;

	public Users() {
		super();
	}

	public Users(String username, Profile profile, Todo_list todo_list,
			User_Achievement user_achievement, Hero hero, Long points) {
		super();
		this.username = username;
		this.profile = profile;
		this.todo_list = todo_list;
		this.user_achievement = user_achievement;
		this.hero = hero;
		this.points = points;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Todo_list getTodo_list() {
		return todo_list;
	}

	public void setTodo_list(Todo_list todo_list) {
		this.todo_list = todo_list;
	}

	public User_Achievement getUser_achievement() {
		return user_achievement;
	}

	public void setUser_achievement(User_Achievement user_achievement) {
		this.user_achievement = user_achievement;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public Set<Missteps> getMissteps() {
		return missteps;
	}
	
	public void addMissteps(Missteps missteps){
		this.missteps.add(missteps);
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", profile="
				+ profile + ", todo_list=" + todo_list + ", user_achievement="
				+ user_achievement + ", hero=" + hero + ", missteps="
				+ missteps + ", points=" + points + "]";
	}

}
