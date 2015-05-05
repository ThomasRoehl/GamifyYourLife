package de.tro.development.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.tro.development.dao.impl.UserDAO;
import de.tro.development.service.UserSession;

public class PointsControllerTest {

	UserSession userSession;
	UserDAO userDao;
	PointsController pc;
	
	@Before
	public void setUp() throws Exception {
		userSession = new UserSession();
		userDao = mock(UserDAO.class);
		pc = new PointsController();
		pc.setUserDAO(userDao);
		pc.setUserSession(userSession);
	}

	@Test
	public void findPoints() {
		Long points =99L;
		userSession.setUser_id(0);
		when(userDao.getUserPoints(userSession.getUser_id())).thenReturn(points);
		
		pc.init();
		assertEquals(pc.getPoints(), points);
	}

}
