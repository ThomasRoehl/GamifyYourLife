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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User_Achievement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_fk", nullable = false)
	private Users user;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "achievement_fk")
	private Set<Achievement> achievements = new HashSet<Achievement>();

	public User_Achievement() {
		super();
	}

	public User_Achievement(Users user) {
		super();
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<Achievement> getAchievements() {
		return achievements;
	}
	
	public void addAchievements(Achievement achievement){
		this.achievements.add(achievement);
	}

	@Override
	public String toString() {
		return "User_Achievement [id=" + id + ", user=" + user
				+ ", achievements=" + achievements + "]";
	}
	
}
