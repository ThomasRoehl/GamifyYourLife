package de.tro.development.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.tro.development.dao.impl.UserDAO;
import de.tro.development.service.UserSession;

public class ContactsTest {
	
	UserController uc;
	UserDAO uDAO;
	UserSession us;
	

	@Before
	public void setUp() throws Exception {
		uc = new UserController();
		us = new UserSession();
		uDAO = mock(UserDAO.class);
		uc.setUserDAO(uDAO);
		uc.setUserSession(us);
	}

	@Test
	public void findUserSuccess() {
		String username = "max";
		String firstname = "Max";
		String lastname = "Mustermann";
		String mail  = "max@mail.com";
		String points = "1";
		String hero = "superman";
		when(uDAO.findUserDetailsByName(username)).thenReturn(new ArrayList<String>(Arrays.asList(firstname, lastname, mail, points, hero)));
		
		uc.setUsername(username);
		uc.findUser();
		assertEquals(uc.getFirstname(), firstname);
		assertEquals(uc.getLastname(), lastname);
		assertEquals(uc.getMail(), mail);
		assertEquals(uc.getPoints(), (Long)(Long.parseLong(points)));
		assertTrue(uc.isFoundUser());
	}
	
	@Test
	public void findUserFail(){
		String username = "max";
		String firstname = "Max";
		String lastname = "Mustermann";
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
	
	@Test
	public void addUserSuccess(){
		String username = "hans";
		String contactname = "max";
		ArrayList<String> contacts = new ArrayList<String>();
		contacts.add(contactname);
		when(uDAO.addContact(username, contactname)).thenReturn(true);
		when(uDAO.findContacts(username)).thenReturn(contacts);
		
		uc.setUsername(contactname);
		us.setUsername(username);
		uc.addUser();
		assertEquals(contacts, uc.getContacts());
	}
	
	@Test
	public void addUserSelf(){
		String username = "max";
		String contactname = "max";
		ArrayList<String> contacts = new ArrayList<String>();
		contacts.add(contactname);
		when(uDAO.addContact(username, contactname)).thenReturn(true);
		when(uDAO.findContacts(username)).thenReturn(contacts);
		
		uc.setUsername(contactname);
		us.setUsername(username);
		uc.addUser();
		assertTrue(uc.contacts.isEmpty());
	}
	
	@Test
	public void addUserTwice(){
		String username = "hans";
		String contactname = "max";
		ArrayList<String> contacts = new ArrayList<String>();
		contacts.add(contactname);
		when(uDAO.addContact(username, contactname)).thenReturn(true);
		when(uDAO.findContacts(username)).thenReturn(null);
		
		uc.setUsername(contactname);
		us.setUsername(username);
		uc.setContacts(contacts);
		uc.addUser();
		assertEquals(contacts, uc.contacts);
	}

}
