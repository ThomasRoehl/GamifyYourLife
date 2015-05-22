package de.tro.development.dao.interf;

import java.util.List;

import de.tro.development.model.Message;

public interface MessageDAOInterface {
	public List<Message> getUserMessages(int user_id);
}
