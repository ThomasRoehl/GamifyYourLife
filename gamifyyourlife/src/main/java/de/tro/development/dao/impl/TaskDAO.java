package de.tro.development.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.TaskDAOInterface;
import de.tro.development.model.Task;
import de.tro.development.model.Todo_list;

@ManagedBean
@SessionScoped
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
				System.out.println("tl found");
				Todo_list tl = query.getResultList().get(0);
				tl.addTasks(t);
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
