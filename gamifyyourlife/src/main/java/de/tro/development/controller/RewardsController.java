package de.tro.development.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.tro.development.dao.impl.RewardDAO;
import de.tro.development.model.Achievement;
import de.tro.development.model.Reward;
import de.tro.development.service.UserSession;


@ManagedBean
@ViewScoped
public class RewardsController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Achievement pickedAchievement;
	private List<Achievement> achievements = new ArrayList<Achievement>();
	private List<Reward> rewards = new ArrayList<Reward>();
	
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	@ManagedProperty(value = "#{rewardDAO}")
	private RewardDAO rewardDAO;

	@PostConstruct
	public void init(){
		achievements = rewardDAO.getAchievements(userSession.getUser_id());
	}
	
	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}
	
	public RewardDAO getRewardDAO() {
		return rewardDAO;
	}

	public void setRewardDAO(RewardDAO rewardDAO) {
		this.rewardDAO = rewardDAO;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Achievement getPickedAchievement() {
		return pickedAchievement;
	}
	public void setPickedAchievement(Achievement pickedAchievement) {
		this.pickedAchievement = pickedAchievement;
	}
	public List<Achievement> getAchievements() {
		return achievements;
	}
	public void setAchievements(ArrayList<Achievement> achievements) {
		this.achievements = achievements;
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
	
	public String createReward(){
		Reward r = new Reward();
		r.setName(name);
		r.setDescription(description);
		r.setAchievement(pickedAchievement);
		r.setUserID(userSession.getUser_id());
		System.out.println(r);
		return null;
	}
}
