package com.sportradar.scoreboard;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ScoreBoardTest {

	private ScoreBoard scoreBoard;
	
	@Before
    public void setUp() {
        scoreBoard = new ScoreBoard();
    }
	
	@Test
    public void testStartGame() {
        scoreBoard.startGame("Mexico", "Canada");
        assertEquals(1, scoreBoard.getSummary().size());
    }
	
	@Test
	public void testStartGameInitialScore() {
	    scoreBoard.startGame("Mexico", "Canada");

	    List<FootballMatch> summary = scoreBoard.getSummary();
	    assertEquals("Mexico 0 - Canada 0", summary.get(0).toString());
	}
	
	@Test
    public void testStartGameWithSameTeams() {
        scoreBoard.startGame("Mexico", "Mexico");
        assertEquals(0, scoreBoard.getSummary().size());
    }
	
	@Test
    public void testStartGameWithSameTeamsAgain() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Mexico", "Canada");
        
        assertEquals(1, scoreBoard.getSummary().size());
    }

	
}
