package de.tro.development.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.tro.development.dao.impl.TaskDAO;
import de.tro.development.model.Category;
import de.tro.development.model.Task;
import de.tro.development.service.UserSession;
import static org.mockito.Mockito.*;

public class TaskControllerTest {

	TaskController tc;
	NavigationController navi;
	TaskDAO taskDao;
	UserSession userSession;
	
	@Before
	public void setUp() throws Exception {
		tc = new TaskController();
		navi = new NavigationController();
		taskDao = mock(TaskDAO.class);
		userSession = new UserSession();
		tc.setNavi(navi);
		tc.setTaskDAO(taskDao);
		tc.setUserSession(userSession);
	}

	@Test
	public void create() {
		userSession.setTodo_list_id(99);
		Task t = new Task();
		when(taskDao.createTask(t, userSession.getTodo_list_id())).thenReturn(true);
		
		assertEquals(true, tc.insertTask(t));
	}
	
	@Test
	public void createFail(){
		userSession.setTodo_list_id(99);
		Task t = new Task();
		when(taskDao.createTask(t, userSession.getTodo_list_id())).thenReturn(false);
		
		assertEquals(false, tc.insertTask(t));
	}
	
	@Test
	public void update(){
		userSession.setTodo_list_id(99);
		Category cat = new Category();
		Task t1 = new Task(cat, "t1", "", 1L, null);
		Task t2 = new Task(cat, "t2", "", 1L, null);
		Task t3 = new Task(cat, "t3", "", 1L, null);
		ArrayList<Task> tasks = new ArrayList<Task>(Arrays.asList(t1, t2, t3));
		Set<Task> stasks = new HashSet<Task>();
		stasks.add(t1);stasks.add(t2);stasks.add(t3);
		when(taskDao.updateTask(userSession.getTodo_list_id())).thenReturn(stasks);
		
		assertEquals(tasks.get(0).toString(), tc.getTasks().get(0).toString());
		assertEquals(tasks.get(1).toString(), tc.getTasks().get(1).toString());
		assertEquals(tasks.get(2).toString(), tc.getTasks().get(2).toString());
		assertEquals(tasks.toString(), tc.getTasks().toString());
	}
	
	@Test
	public void update2(){
		userSession.setTodo_list_id(99);
		Category cat = new Category();
		Task t1 = new Task(cat, "t1", "", 1L, null);
		Task t2 = new Task(cat, "t2", "", 1L, null);
		Task t3 = new Task(cat, "t3", "", 1L, null);
		ArrayList<Task> tasks = new ArrayList<Task>(Arrays.asList(t1, t2, t3));
		Set<Task> stasks = new HashSet<Task>();
		stasks.add(t1);stasks.add(t2);
		when(taskDao.updateTask(userSession.getTodo_list_id())).thenReturn(stasks);
		
		assertEquals(tasks.get(0).toString(), tc.getTasks().get(0).toString());
		assertEquals(tasks.get(1).toString(), tc.getTasks().get(1).toString());
		assertTrue(tc.getTasks().size() == 2);
	}

}
