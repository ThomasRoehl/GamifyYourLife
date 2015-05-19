package de.tro.development.dao.interf;

import java.util.List;

import de.tro.development.model.Achievement;
import de.tro.development.model.Reward;

public interface RewardDAOInterface {
	
	public List<Achievement> getAchievements(int user_id);
	public boolean createReward(Reward reward);
	public List<Reward> getRewards(int user_id);
	public Achievement getAchievementByName(String name);

}
