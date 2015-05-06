package de.tro.development.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalendarControllerTest.class, PointsControllerTest.class,
		TaskControllerTest.class, UserControllerTest.class })
public class AllTests {

}
