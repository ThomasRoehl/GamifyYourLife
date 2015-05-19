package de.tro.development.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import de.tro.development.dao.impl.RewardDAO;
import de.tro.development.model.Achievement;
import de.tro.development.model.Reward;
import de.tro.development.service.UserSession;


@ManagedBean(name="rewardsController")
@ViewScoped
public class RewardsController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Achievement pickedAchievement;
	private String pickedName;
	private List<String> achievements_name = new ArrayList<String>();
	private List<Achievement> achievements = new ArrayList<Achievement>();
	private List<Reward> rewards = new ArrayList<Reward>();
	
	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;
	@ManagedProperty(value = "#{navigationController}")
	private NavigationController navi;
	@ManagedProperty(value = "#{rewardDAO}")
	private RewardDAO rewardDAO;

	/**
	 * load achievements from db
	 */
	@PostConstruct
	public void init(){
		achievements = rewardDAO.getAchievements(userSession.getUser_id());
		for (Achievement a: achievements){
			achievements_name.add(a.getName());
		}
	}
	
	public List<String> getAchievements_name() {
		return achievements_name;
	}

	public void setAchievements_name(List<String> achievements_name) {
		this.achievements_name = achievements_name;
	}
	
	public String getPickedName() {
		return pickedName;
	}

	public void setPickedName(String pickedName) {
		this.pickedName = pickedName;
	}
	
	public List<Reward> getRewards() {
		rewards = rewardDAO.getRewards(userSession.getUser_id());

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
	
	/**
	 * create Reward an register in DB
	 * @return
	 */
	public String createReward(){
		Reward r = new Reward();
		r.setName(name);
		r.setDescription(description);
		r.setAchievement(rewardDAO.getAchievementByName(pickedName));
		r.setUserID(userSession.getUser_id());
		System.out.println(rewardDAO.createReward(r));
		return null;
	}
}
