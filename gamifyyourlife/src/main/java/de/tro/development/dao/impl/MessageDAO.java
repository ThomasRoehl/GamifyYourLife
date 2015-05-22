package de.tro.development.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import de.tro.development.dao.interf.MessageDAOInterface;
import de.tro.development.model.Message;
import de.tro.development.model.UserProfile;

@ManagedBean
@ApplicationScoped
public class MessageDAO implements MessageDAOInterface {
	
	@PersistenceContext( unitName="gamifyyourlife")
	protected  EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	@Override
	public List<Message> getUserMessages(int user_id){
		List<Message> messages = new ArrayList<Message>();
		try {
			TypedQuery<UserProfile> query = em.createNamedQuery("UserProfile.findUserByID", UserProfile.class);
			query.setParameter("user_id", user_id);
			TypedQuery<Message> msgQuery = em.createNamedQuery("Message.getMessagesFromUserByID", Message.class);
			msgQuery.setParameter("addressee", query.getResultList().get(0));
			messages.addAll(msgQuery.getResultList());
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public boolean sendMessage(String addressee, String sender, Message msg){
		try {
			TypedQuery<UserProfile> ad = em.createNamedQuery("UserProfile.findUserProfileByName", UserProfile.class);
			ad.setParameter("username", addressee);
			TypedQuery<UserProfile> se = em.createNamedQuery("UserProfile.findUserProfileByName", UserProfile.class);
			se.setParameter("username", sender);
			msg.setSender(se.getResultList().get(0));
			msg.setAddressee(ad.getResultList().get(0));
			utx.begin();
			em.merge(msg);
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

}
