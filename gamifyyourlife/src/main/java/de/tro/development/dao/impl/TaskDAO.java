package de.tro.development.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.TaskDAOInterface;
import de.tro.development.model.Category;
import de.tro.development.model.Task;
import de.tro.development.model.Todo_list;

@ManagedBean
@ApplicationScoped
public class TaskDAO implements TaskDAOInterface {

	@PersistenceContext(unitName = "gamifyyourlife")
	protected EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public boolean createTask(Task t, int id) {
		
		try {
			TypedQuery<Todo_list> query = em.createNamedQuery(
					"Todo_list.findAllTasksByID", Todo_list.class);
			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				Todo_list tl = query.getResultList().get(0);
				tl.addTasks(t);
				utx.begin();
				em.merge(tl);
				utx.commit();
				return true;
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
		}
		
		return false;
	}

	@Override
	public Set<Task> updateTask(int id) {
		try {
			TypedQuery<Todo_list> query = em.createNamedQuery(
					"Todo_list.findAllTasksByID", Todo_list.class);
			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList().get(0).getTasks();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
