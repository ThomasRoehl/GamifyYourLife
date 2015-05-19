package de.tro.development.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.RewardDAOInterface;
import de.tro.development.model.Achievement;
import de.tro.development.model.Reward;

@ManagedBean
@ApplicationScoped
public class RewardDAO implements RewardDAOInterface {

	@PersistenceContext(unitName = "gamifyyourlife")
	protected EntityManager em;

	@Resource
	private UserTransaction utx;

	@SuppressWarnings("unchecked")
	@Override
	public List<Achievement> getAchievements(int user_id) {
		try {
			Query query = em.createNamedQuery("UserProfile.getAchievements");
			query.setParameter("id", user_id);
			List<Integer> user_achievements = query.getResultList();
			if (user_achievements.isEmpty()) {
				TypedQuery<Achievement> t1query = em.createNamedQuery(
						"Achievement.getAllAchievements", Achievement.class);
				return t1query.getResultList();
			}
			else{
				TypedQuery<Achievement> t2query = em.createNamedQuery(
						"Achievement.getAchievementsNotFromIDList",
						Achievement.class);
				t2query.setParameter("idList", user_achievements);
				return t2query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createReward(Reward reward) {
		try {
			Query query = em.createNamedQuery("Reward.checkUserRewardExists",
					Reward.class);
			query.setParameter("achievement", reward.getAchievement());
			query.setParameter("user_id", reward.getUserID());
			if ((Long) query.getResultList().get(0) == 0) {
				utx.begin();
				em.merge(reward);
				utx.commit();
				return true;
			} else {
				System.out.println(query.getResultList().get(0));
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Reward> getRewards(int user_id) {
		try {
			TypedQuery<Reward> query = em.createNamedQuery(
					"Reward.getRewardsByUserID", Reward.class);
			query.setParameter("user_id", user_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Achievement getAchievementByName(String name) {
		try {
			TypedQuery<Achievement> tquery = em.createNamedQuery(
					"Achievement.getAchievementByName", Achievement.class);
			tquery.setParameter("name", name);
			return tquery.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
