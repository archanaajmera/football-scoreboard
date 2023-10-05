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
    public void testStartMultipleGames() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");

        assertEquals(2, scoreBoard.getSummary().size());
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
	
	@Test(expected = IllegalArgumentException.class)
    public void testStartGameWithEmptyTeamNames() {
        scoreBoard.startGame("", "Canada");
        scoreBoard.startGame("Mexico", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartGameWithNullTeamNames() {
        scoreBoard.startGame(null, "Canada");
        scoreBoard.startGame("Mexico", null);
    }
    
    @Test
    public void testStartGameWithWhitespaceInTeamNames() {
        scoreBoard.startGame("  Mexico  ", "  Canada  ");

        List<FootballMatch> summary = scoreBoard.getSummary();
        assertEquals(1, summary.size());

        FootballMatch match = summary.get(0);
        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
    }
    
    @Test
    public void testCaseInsensitiveTeamNames() {
    	
        scoreBoard.startGame("MEXIco", "canada");
        
        try {
            scoreBoard.startGame("mexico", "CANADA");
            fail("Expected IllegalArgumentException to be thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("A game with the same teams already exists.", e.getMessage());
        }

        assertEquals(1, scoreBoard.getSummary().size());
    }

	
}
