package de.tro.development.dao.interf;

import java.util.List;
import java.util.Set;

import de.tro.development.model.Task;

public interface TaskDAOInterface {
	
	public boolean createTask(Task t, int user_id);
	public Set<Task> updateTask(int user_id);
}
