package de.tro.development.dao.impl;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.UserDAOInterface;
import de.tro.development.model.Todo_list;
import de.tro.development.model.UserProfile;

@ManagedBean
@ApplicationScoped
public class UserDAO implements UserDAOInterface {

	@PersistenceContext(unitName = "gamifyyourlife")
	protected EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public boolean login(String username, String password) {
		try {
			Query query = em.createNamedQuery("UserProfile.checkUserLogin");
			query.setParameter("username", username);
			query.setParameter("password", password);
			if (!(query.getResultList().get(0).equals(new Long(0))))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (em == null)
				System.out.println("-------- em null");
		}
		return false;
	}

	@Override
	public boolean createUser(UserProfile user) {
		try {
			int id = findUserID(user.getUsername()); 
			if ( id == -1) {
				utx.begin();
				em.persist(user);
				utx.commit();
			}
			else{
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int findUserID(String username) {
		try {
			Query query = em.createNamedQuery("UserProfile.findUserByName");
			query.setParameter("username", username);

			if (!query.getResultList().isEmpty()) {
				return (Integer) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public int findTodo_list(String username) {
		try {
			Query query = em.createNamedQuery("UserProfile.findTodoListByName");
			query.setParameter("username", username);

			if (!query.getResultList().isEmpty()) {
				return ((Todo_list) query.getResultList().get(0)).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public long getUserPoints(int user_id) {
		try {
			Query query = em.createNamedQuery("UserProfile.findUserPoints");
			query.setParameter("id", user_id);

			if (!query.getResultList().isEmpty()) {
				return (Long) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

}
