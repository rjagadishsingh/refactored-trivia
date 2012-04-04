package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class TestGameRollWhenNotInPenaltyBox {

	private Game game;
	private StubGameQuestions stubGameQuestions;

	private class StubGameQuestions extends GameQuestions {
		
		public boolean askQuestionIsCalled;

		public void askQuestion(int currentPlayerPlace) {
			askQuestionIsCalled = true;
		}
		
		public String currentCategory(int currentPlayerPlace) {
			return "Category1";
		}
		
	}
	
	@Before public void createStubGameQuestionsAndGameAndThenAddOnePlayer() {
		stubGameQuestions = new StubGameQuestions();
		game = new Game(stubGameQuestions, new GamePlayers());
		game.add("Player1");
	}
	
	@Test public void output_Message() throws IOException {
		ByteArrayOutputStream spyOutput = AllTestsHelper.createSpySystemOut();
		
		game.rollWhenNotInPenaltyBox(1);
		
		assertEquals("Player1's new location is 1" + AllTestsHelper.LINE_SEPARATOR +
					"The category is Category1" + AllTestsHelper.LINE_SEPARATOR, spyOutput.toString());
		
		AllTestsHelper.restoreSystemOutAndCloseSpyOutput(spyOutput);
	}

	@Test public void ask_Question_Is_Called() {
		game.rollWhenNotInPenaltyBox(1);
		
		assertTrue(stubGameQuestions.askQuestionIsCalled);
	}
	
	@Test public void current_Player_Places_When_Roll_1() {
		game.rollWhenNotInPenaltyBox(1);
		
		assertEquals(1, game.places[game.currentPlayer]);
	}

	@Test public void current_Player_Places_When_Roll_11() {
		game.rollWhenNotInPenaltyBox(11);
		
		assertEquals(11, game.places[game.currentPlayer]);
	}
	
	@Test public void current_Player_Places_When_Roll_5_Then_6() {
		game.rollWhenNotInPenaltyBox(5);
		game.rollWhenNotInPenaltyBox(6);
		
		assertEquals(11, game.places[game.currentPlayer]);
	}
	
	@Test public void current_Player_Places_When_Roll_12() {
		game.rollWhenNotInPenaltyBox(12);
		
		assertEquals(0, game.places[game.currentPlayer]);
	}
	
	@Test public void current_Player_Places_When_Roll_6_Then_6() {
		game.rollWhenNotInPenaltyBox(6);
		game.rollWhenNotInPenaltyBox(6);
		
		assertEquals(0, game.places[game.currentPlayer]);
	}
	
}
