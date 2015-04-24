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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@NamedQuery(name = "Todo_list.findAllTasksByID", query = "SELECT t FROM Todo_list t WHERE t.id = :id")
@Entity
public class Todo_list implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "todo_list_fk")
	private Set<Task> tasks = new HashSet<Task>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "date_fk")
	private Set<UserDate> dates = new HashSet<UserDate>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "rewards_fk")
	private Set<Reward> rewards = new HashSet<Reward>();
	
	@OneToOne(optional=false, mappedBy="todo_list")
	private UserProfile user;

	public int getId() {
		return id;
	}

	public Set<Task> getTasks() {
		return tasks;
	}
	
	public void addTasks(Task tasks){
		this.tasks.add(tasks);
	}

	public Set<UserDate> getDates() {
		return dates;
	}
	
	public void addDates(UserDate dates){
		this.dates.add(dates);
	}

	public Set<Reward> getRewards() {
		return rewards;
	}
	
	public void addRewards(Reward rewards){
		this.rewards.add(rewards);
	}

	@Override
	public String toString() {
		return "Todo_list [id=" + id + ", tasks=" + tasks + ", dates=" + dates
				+ ", rewards=" + rewards + "]";
	}
}
