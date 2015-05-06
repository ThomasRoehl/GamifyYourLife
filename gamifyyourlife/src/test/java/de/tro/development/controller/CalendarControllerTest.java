package de.tro.development.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.tro.development.dao.impl.CalendarDAO;
import de.tro.development.model.Category;
import de.tro.development.model.UserDate;
import de.tro.development.service.UserSession;

public class CalendarControllerTest {

	CalendarController cc;
	NavigationController navi;
	CalendarDAO cDAO;
	UserSession userSession;

	@Before
	public void setUp() throws Exception {
		cc = new CalendarController();
		navi = new NavigationController();
		cDAO = mock(CalendarDAO.class);
		userSession = new UserSession();
		cc.setNavi(navi);
		cc.setCalendarDAO(cDAO);
		cc.setUserSession(userSession);
		userSession.setTodo_list_id(1);
	}

	@Test
	public void createDate() {
		cc.setCategory("test");
		cc.setDateBegin(new Date(System.currentTimeMillis()));
		cc.setDateEnd(new Date(System.currentTimeMillis() + 100000));
		cc.setName("test");
		cc.setDescription("testdesc");
		cc.setDayEvent(false);
		cc.setPoints(12L);

		UserDate date = new UserDate(cc.getName(), cc.getDescription(),
				new Category(cc.getCategory(), ""), cc.getPoints(),
				cc.getDateBegin(), cc.getDateEnd(), false);
		when(cDAO.createNewDate(date, userSession.getTodo_list_id()))
				.thenReturn(true);

		assertTrue(cc.createDate(date));
	}

	@Test
	public void loadDates() {
		cc.setCategory("test");
		cc.setDateBegin(new Date(System.currentTimeMillis()));
		cc.setDateEnd(new Date(System.currentTimeMillis() + 100000));
		cc.setName("test");
		cc.setDescription("testdesc");
		cc.setDayEvent(true);
		cc.setPoints(12L);

		UserDate date = new UserDate(cc.getName(), cc.getDescription(),
				new Category(cc.getCategory(), ""), cc.getPoints(),
				cc.getDateBegin(), cc.getDateEnd(), true);
		Set<UserDate> s = new HashSet<UserDate>();
		s.add(date);
		
		when(cDAO.getUserDatesByID(userSession.getTodo_list_id())).thenReturn(s);
		cc.init();
		assertEquals(s, cc.getUserDates());
	}

	@Test
	public void wrongDates(){
		cc.setDateBegin(new Date(System.currentTimeMillis()));
		cc.setDateEnd(new Date(System.currentTimeMillis() - 100000));
		assertEquals(null, cc.showNewDate());
	}
	
	@Test
	public void wrongDatesDayEvent(){
		cc.setDateBegin(new Date(System.currentTimeMillis()));
		assertEquals(null, cc.showNewDate());
		cc.setDateEnd(new Date(System.currentTimeMillis() + 100000));
		when(cDAO.createNewDate(any(UserDate.class), any(Integer.class))).thenReturn(true);
		assertEquals(navi.moveToCalendar(), cc.showNewDate());
	}
}
