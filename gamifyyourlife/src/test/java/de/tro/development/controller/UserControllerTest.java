package de.tro.development.controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import de.tro.development.dao.impl.UserDAO;
import de.tro.development.model.UserProfile;
import de.tro.development.service.UserSession;

public class UserControllerTest {
	
	UserController uc;
	NavigationController navi;
	UserDAO userDao;
	UserSession userSession;

	@Before
	public void setUp() throws Exception {
		uc = new UserController();
		userDao = mock(UserDAO.class);
		userSession = new UserSession();
		navi = new NavigationController();
		uc.setUserDAO(userDao);
		uc.setUserSession(userSession);
		uc.setNavi(navi);
	}

	@Test
	public void login() {
		
		uc.setPassword("test");
		uc.setUsername("testuser");
		
		when(userDao.login(uc.getUsername(), uc.getPassword())).thenReturn(true);
		
		assertEquals(navi.moveToHome(), uc.login());
	}
	
	@Test
	public void loginFail(){
		uc.setPassword("test");
		uc.setUsername("testuser");
		when(userDao.login(uc.getUsername(), uc.getPassword())).thenReturn(false);
		
		assertEquals(null, uc.login());
	}
	
	@Test
	public void logout() {
		assertEquals("index", uc.logout());
		assertEquals("", uc.getPassword());
		assertEquals("", uc.getUsername());
	}
	
	@Test
	public void clearData(){
		uc.setFirstname("test");
		uc.setLastname("test");
		uc.setMail("maiL");
		uc.setStreet("street");
		uc.setPassword("pas");
		uc.setUsername("name");
		
		uc.clearData();
		assertEquals("", uc.getFirstname());
		assertEquals("", uc.getLastname());
		assertEquals("", uc.getMail());
		assertEquals("", uc.getStreet());
		uc.clearLogin();
		assertEquals("", uc.getUsername());
		assertEquals("", uc.getPassword());
		assertEquals("", userSession.getUsername());
	}
	
	@Test
	public void createUser(){
		uc.setFirstname("test");
		uc.setLastname("test");
		uc.setMail("maiL");
		uc.setStreet("street");
		uc.setPassword("pas");
		uc.setUsername("name");
		when(userDao.createUser(new UserProfile())).thenReturn(true);
		
		assertEquals(uc.registerUser(), navi.moveToIndex());
		assertEquals("", uc.getFirstname());
		assertEquals("", uc.getLastname());
		assertEquals("", uc.getMail());
		assertEquals("", uc.getStreet());
		assertEquals("name", uc.getUsername());
		assertEquals("pas", uc.getPassword());
	}

}
