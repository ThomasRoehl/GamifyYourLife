package de.tro.development.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.CategoryDAOInterface;
import de.tro.development.model.Category;

@ManagedBean
@ApplicationScoped
public class CategoryDAO implements CategoryDAOInterface {

	@PersistenceContext(unitName = "gamifyyourlife")
	protected EntityManager em;

	@Resource
	private UserTransaction utx;
	
	@Override
	public List<String> getCategories(){
		List<String> res = new ArrayList<String>();
		try {
			TypedQuery<Category> query = em.createNamedQuery(
					"Category.getAllCategories", Category.class);
			if (!query.getResultList().isEmpty()){
				for (Category c: query.getResultList()){
					res.add(c.getName());
				}
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public Category getCategoryByName(String name){
		try {
			TypedQuery<Category> query = em.createNamedQuery(
					"Category.getCategoryByName", Category.class);
			query.setParameter("name", name);
			if (!query.getResultList().isEmpty()){
				return query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
