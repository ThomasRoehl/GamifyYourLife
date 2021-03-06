package de.tro.development.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.UserDAOInterface;
import de.tro.development.model.Hero;
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
		}
		return false;
	}

	@Override
	public boolean createUser(UserProfile user) {
		try {
			int id = findUserID(user.getUsername());
			if (id == -1) {
				utx.begin();
				em.merge(user);
				utx.commit();
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	@Override
	public List<String> findUserDetailsByName(String username) {
		try {
			TypedQuery<UserProfile> query = em.createNamedQuery(
					"UserProfile.findUserProfileByName", UserProfile.class);
			query.setParameter("username", username);
			if (!query.getResultList().isEmpty()) {
				List<String> res = new ArrayList<String>();
				res.add(query.getResultList().get(0).getFirstname());
				res.add(query.getResultList().get(0).getLastname());
				res.add(query.getResultList().get(0).getMail());
				res.add(query.getResultList().get(0).getPoints().toString());
				res.add(query.getResultList().get(0).getHero().getName());
				return res;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> findContacts(String username) {
		try {
			TypedQuery<UserProfile> query = em.createNamedQuery(
					"UserProfile.findUserProfileByName", UserProfile.class);
			query.setParameter("username", username);
			if (!query.getResultList().isEmpty()) {
				Set<UserProfile> res = query.getResultList().get(0)
						.getFriends();
				List<String> contacts = new ArrayList<String>();
				for (UserProfile u : res) {
					contacts.add(u.getUsername());
				}
				return contacts;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUsernameByID(int user_id) {
		try {
			Query query = em.createNamedQuery("UserProfile.findUsernameByID");
			query.setParameter("user_id", user_id);

			if (!query.getResultList().isEmpty()) {
				return (String) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean addContact(String username, String contactname) {
		try {
			TypedQuery<UserProfile> query = em.createNamedQuery(
					"UserProfile.findUserProfileByName", UserProfile.class);
			query.setParameter("username", username);
			TypedQuery<UserProfile> query1 = em.createNamedQuery(
					"UserProfile.findUserProfileByName", UserProfile.class);
			query1.setParameter("username", contactname);
			System.out.println("queries init done");
			if (!query.getResultList().isEmpty()
					&& !query1.getResultList().isEmpty()) {
				UserProfile u = query.getResultList().get(0);
				UserProfile c = query1.getResultList().get(0);
				u.addFriends(c);
				utx.begin();
				em.merge(u);
				utx.commit();
			}
			return true;
		} catch (Exception e) {
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> findUsersLikeName(String username) {
		try {
			List<String> res = new ArrayList<String>();
			Query query = em.createNamedQuery("UserProfile.findUserLikeName");
			query.setParameter("username", ("%" + username.toUpperCase() + "%"));
			List<Integer> ids = new ArrayList<Integer>();
			
			if (!query.getResultList().isEmpty()) {
				for (Object o: query.getResultList()){
					ids.add((Integer)o);
				}
			}
			if (!ids.isEmpty()){
				for (Integer i: ids){
					res.add(getUsernameByID(i));
				}				
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findUserInformationByID(Integer id) {
		try {
			Query query = em.createNamedQuery("UserProfile.findUserinformationByID");
			query.setParameter("user_id", id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public List<String> getAllHeroes(){
		try {
			TypedQuery<Hero> query = em.createNamedQuery("Hero.getAllHeroes", Hero.class);
			List<String> res = new ArrayList<String>();
			for (Hero h: query.getResultList()){
				res.add(h.getName());
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Hero getHeroByName(String name){
		try {
			TypedQuery<Hero> query = em.createNamedQuery("Hero.getHeroByName", Hero.class);
			query.setParameter("name", name);
			if (!query.getResultList().isEmpty()){
				return query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getHeroNameByUserID(Integer user_id) {
		try {
			TypedQuery<Hero> query = em.createNamedQuery("UserProfile.getHero", Hero.class);
			query.setParameter("id", user_id);
			if (!query.getResultList().isEmpty()){
				return query.getResultList().get(0).getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
