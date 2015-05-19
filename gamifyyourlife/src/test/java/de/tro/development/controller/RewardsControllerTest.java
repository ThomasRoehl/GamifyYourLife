package de.tro.development.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.tro.development.dao.impl.RewardDAO;
import de.tro.development.dao.impl.UserDAO;
import de.tro.development.model.Achievement;
import de.tro.development.model.Reward;
import de.tro.development.model.UserDate;
import de.tro.development.service.UserSession;

public class RewardsControllerTest {

	RewardsController rc;
	RewardDAO rd;
	UserSession us;
	NavigationController nc;
	
	@Before
	public void setUp() throws Exception {
		rc = new RewardsController();
		us = new UserSession();
		nc = new NavigationController();
		rd = mock(RewardDAO.class);
		rc.setRewardDAO(rd);
		rc.setUserSession(us);
		rc.setNavi(nc);
		us.setUser_id(100);
	}

	
	@Test
	public void createReward() {
		
		String name = "reward";
		String desc = "this is a test reward";
		Achievement ac = new Achievement();
		ac.setName("achievement");
		ac.setPoints_needed(10L);
		
		when(rd.getAchievementByName(ac.getName())).thenReturn(ac);
		when(rd.createReward(any(Reward.class))).thenReturn(true);
		
		rc.setDescription(desc);
		rc.setName(name);
		rc.setPickedName(ac.getName());
		assertEquals(null, rc.createReward());
	}

}
