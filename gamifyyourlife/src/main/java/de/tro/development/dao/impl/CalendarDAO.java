package de.tro.development.dao.impl;

import java.util.Set;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.CalendarDAOInterface;
import de.tro.development.model.Todo_list;
import de.tro.development.model.UserDate;

@ManagedBean
@ApplicationScoped
public class CalendarDAO implements CalendarDAOInterface{

	@PersistenceContext( unitName="gamifyyourlife")
	protected  EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	@Override
	public boolean createNewDate(UserDate date, int id) {
		try {
			TypedQuery<Todo_list> query = em.createNamedQuery(
					"Todo_list.findAllDatesByID", Todo_list.class);
			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				Todo_list tl = query.getResultList().get(0);
				tl.addDates(date);
				utx.begin();
				em.merge(tl);
				utx.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public Set<UserDate> getUserDatesByID(int id) {
		try {
			TypedQuery<Todo_list> query = em.createNamedQuery(
					"Todo_list.findAllDatesByID", Todo_list.class);
			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList().get(0).getDates();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
