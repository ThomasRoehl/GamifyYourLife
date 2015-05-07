package de.tro.development.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.tro.development.dao.impl.UserDAO;

public class ContactsTest {
	
	UserController uc;
	UserDAO uDAO;
	

	@Before
	public void setUp() throws Exception {
		uc = new UserController();
		uDAO = mock(UserDAO.class);
		uc.setUserDAO(uDAO);
	}

	@Test
	public void findUserSuccess() {
		String username = "test";
		String firstname = "firstname";
		String lastname = "lastname";
		when(uDAO.findUserDetailsByName(username)).thenReturn(new ArrayList<String>(Arrays.asList(firstname, lastname)));
		
		uc.setUsername(username);
		uc.findUser();
		assertEquals(uc.getFirstname(), firstname);
		assertEquals(uc.getLastname(), lastname);
		assertTrue(uc.isFoundUser());
	}
	
	@Test
	public void findUserFail(){
		String username = "test";
		String firstname = "firstname";
		String lastname = "lastname";
		when(uDAO.findUserDetailsByName(username)).thenReturn(null);
		
		uc.clearData(1);
		uc.setUsername(username);
		uc.findUser();
		assertNotEquals(uc.getFirstname(), firstname);
		assertNotEquals(uc.getLastname(), lastname);
		assertEquals(uc.getFirstname(), "");
		assertEquals(uc.getLastname(), "");
		assertEquals(uc.getUsername(), "");
		assertFalse(uc.isFoundUser());
	}

}
