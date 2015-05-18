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
			if (!user_achievements.isEmpty()) {
				TypedQuery<Achievement> t1query = em.createNamedQuery(
						"Achievement.getAllAchievements", Achievement.class);
				return t1query.getResultList();
			}
			TypedQuery<Achievement> t2query = em.createNamedQuery(
					"Achievement.getAchievementsNotFromIDList", Achievement.class);
			t2query.setParameter("idList", user_achievements);
			return t2query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createReward(Reward reward) {
		try {
			utx.begin();
			em.persist(reward);
			utx.commit();
			return true;
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
	public List<Reward> getRewards(int todo_list_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
