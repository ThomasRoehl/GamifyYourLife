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
	
	public void setUserData(String name, String pw){
		uc.setFirstname("test");
		uc.setLastname("test");
		uc.setMail("maiL");
		uc.setStreet("street");
		uc.setPassword(pw);
		uc.setUsername(name);
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
		assertEquals(navi.moveToIndex(), uc.logout());
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
		
		uc.clearData(1);
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
		when(userDao.createUser(any(UserProfile.class))).thenReturn(true);
		
		assertEquals(uc.registerUser(), navi.moveToIndex());
		assertEquals("", uc.getFirstname());
		assertEquals("", uc.getLastname());
		assertEquals("", uc.getMail());
		assertEquals("", uc.getStreet());
		assertEquals("name", uc.getUsername());
		assertEquals("pas", uc.getPassword());
	}
	
	@Test
	public void createFail(){
		setUserData("name", "pw");
		when(userDao.findUserID("name")).thenReturn(1);
		
		assertFalse(uc.createUser());
	}

}
