package com.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ScoreBoard {

	private List<FootballMatch> matches;
	private static final Logger logger = Logger.getLogger(ScoreBoard.class.getName());
	
	public ScoreBoard() {
        this.matches = new ArrayList<>();
    }
		
	public void startGame(String homeTeam, String awayTeam) {
		
		if(homeTeam.equals(awayTeam)) {
			System.out.println("Both home team and away team names cann't same");
			return;
		}
		
		if (isGameExists(homeTeam, awayTeam)) {
            // Log a message if a game with the same teams already exists.
            logger.warning("A game with the same team names already exists: " + homeTeam + " - " + awayTeam);
            return;
        }
		
		FootballMatch match = new FootballMatch(homeTeam, awayTeam);
        matches.add(match);
		
	}
	
	
	// Method to check if a game with the given team names already exists.
    private boolean isGameExists(String homeTeam, String awayTeam) {
        for (FootballMatch match : matches) {
            if (match.getHomeTeam().equalsIgnoreCase(homeTeam) && match.getAwayTeam().equalsIgnoreCase(awayTeam)) {
                return true;
            }
        }
        return false;
    }
    
	public List<FootballMatch> getSummary() {
	    return matches;
	}
	
}
