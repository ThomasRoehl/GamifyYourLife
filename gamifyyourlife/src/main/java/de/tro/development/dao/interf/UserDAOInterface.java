package de.tro.development.dao.interf;

import java.util.List;

import de.tro.development.model.UserProfile;

public interface UserDAOInterface {
	
	public boolean login(String username, String password);
	public boolean createUser(UserProfile user);
	public int findUserID(String username);
	public int findTodo_list(String username);
	public long getUserPoints(int user_id);
	public List<String> findUserProfileByName(String username);

}
