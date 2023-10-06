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
    	
        scoreBoard.startGame("mexico", "canada");
        
        try {
            scoreBoard.startGame("MEXICO", "CANADA");
        } catch (IllegalArgumentException e) {
            assertEquals("A game with the same teams already exists.", e.getMessage());
        }

        assertEquals(1, scoreBoard.getSummary().size());
        FootballMatch match = scoreBoard.getSummary().get(0);
        assertEquals("mexico", match.getHomeTeam());
        assertEquals("canada", match.getAwayTeam());
    }
    
    @Test
    public void testFinishGame() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Canada");
        assertEquals(0, scoreBoard.getSummary().size());
    }

    @Test
    public void testFinishNonExistentGame() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Italy");
        assertEquals(1, scoreBoard.getSummary().size());
    }
    
    @Test
    public void testFinishGameMultipleGames() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.startGame("Germany", "France");

        scoreBoard.finishGame("Spain", "Brazil");
        assertEquals(2, scoreBoard.getSummary().size());
    }
    
    @Test
    public void testFinishGameWithWhitespace() {
        scoreBoard.startGame("  Mexico  ", "  Canada  ");
        scoreBoard.finishGame("Mexico", "Canada");
        assertEquals(0, scoreBoard.getSummary().size());
    }

    @Test
    public void testFinishGameWithDifferentCase() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("mexico", "canada");
        assertEquals(0, scoreBoard.getSummary().size());
    }
    
    @Test
    public void testUpdateScore() {
    	
        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);

        List<FootballMatch> summary = scoreBoard.getSummary();
        assertEquals(1, summary.size());

        FootballMatch match = summary.get(0);
        assertEquals("Germany", match.getHomeTeam());
        assertEquals("France", match.getAwayTeam());
        assertEquals(2, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }
    
    @Test
    public void testUpdateScoreWithMultipleGames() {
    	scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.startGame("Germany", "France");

        scoreBoard.updateScore("Spain", "Brazil", 10, 2);

        List<FootballMatch> summary = scoreBoard.getSummary();
        assertEquals(3, summary.size());

        for (FootballMatch match : summary) {
            if (match.getHomeTeam().equals("Spain") && match.getAwayTeam().equals("Brazil")) {
            	assertEquals(10, match.getHomeScore());
                assertEquals(2, match.getAwayScore());
            }else {
            	 assertEquals(0, match.getHomeScore());
                 assertEquals(0, match.getAwayScore());
            }
        } 
    }
    
    @Test
    public void testUpdateScoreForMultipleMatches() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        assertEquals(0, scoreBoard.getSummary().get(0).getHomeScore());
        assertEquals(5, scoreBoard.getSummary().get(0).getAwayScore());
        assertEquals(10, scoreBoard.getSummary().get(1).getHomeScore());
        assertEquals(2, scoreBoard.getSummary().get(1).getAwayScore());
    }

    @Test
    public void testUpdateScoreWithNegativeScores() {
        scoreBoard.startGame("Spain", "Brazil");
        try {
            scoreBoard.updateScore("Spain", "Brazil", -1, 2);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Scores cannot be negative.", e.getMessage());
        }
    }
    
    @Test
    public void testUpdateScoreForNonExistingMatch() {
        try {
            scoreBoard.updateScore("Spain", "Brazil", 2, 1);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Match does not exist for the provided teams.", e.getMessage());
        }
    }
    
      
   
}
