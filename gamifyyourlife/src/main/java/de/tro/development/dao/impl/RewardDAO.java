package de.tro.development.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
			Query query = em
					.createQuery("SELECT a.achievement_fk FROM USER_ACHIEVEMENT a WHERE a.user_FK = :user_id");
			query.setParameter("user_id", user_id);
			List<Integer> user_achievements = query.getResultList();
			if (!user_achievements.isEmpty()) {

			}
			TypedQuery<Achievement> tquery = em.createNamedQuery(
					"Achievement.getAchievementsNotFromIDList", Achievement.class);
			tquery.setParameter("idList", user_achievements);
			return tquery.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createReward(Reward reward) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reward> getRewards(int todo_list_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
