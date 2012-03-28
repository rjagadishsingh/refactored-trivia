package com.adaptionsoft.games.uglytrivia;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestGameInitialization.class,
	TestGameAdd.class,
	AllTestsForGameRoll.class,
	TestGameWrongAnswer.class,
	TestGameWasCorrectlyAnswered.class,
})
public class AllTests {

}
