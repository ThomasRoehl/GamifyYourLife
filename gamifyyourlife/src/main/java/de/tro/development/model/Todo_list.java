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
	@JoinColumn(name = "task_fk")
	private Set<Tasks> tasks = new HashSet<Tasks>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "date_fk")
	private Set<Dates> dates = new HashSet<Dates>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "rewards_fk")
	private Set<Rewards> rewards = new HashSet<Rewards>();

	public int getId() {
		return id;
	}

	public Set<Tasks> getTasks() {
		return tasks;
	}
	
	public void addTasks(Tasks tasks){
		this.tasks.add(tasks);
	}

	public Set<Dates> getDates() {
		return dates;
	}
	
	public void addDates(Dates dates){
		this.dates.add(dates);
	}

	public Set<Rewards> getRewards() {
		return rewards;
	}
	
	public void addRewards(Rewards rewards){
		this.rewards.add(rewards);
	}

	@Override
	public String toString() {
		return "Todo_list [id=" + id + ", tasks=" + tasks + ", dates=" + dates
				+ ", rewards=" + rewards + "]";
	}
}
