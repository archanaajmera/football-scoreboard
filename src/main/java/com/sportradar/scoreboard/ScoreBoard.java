package com.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ScoreBoard {

	private List<FootballMatch> matches;
	private static final Logger logger = Logger.getLogger(ScoreBoard.class.getName());
	
	public ScoreBoard() {
        this.matches = new ArrayList<>();
    }
		
	public void startGame(String homeTeam, String awayTeam) {
		
		if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Both home team and away team names must be provided.");
        }
		
		if(homeTeam.trim().equalsIgnoreCase(awayTeam.trim())) {
			System.out.println("Home team and away team can not have the same name");
			return;
		}
		
		if (isGameExists(homeTeam, awayTeam)) {
            // Log a message if a game with the same teams already exists.
            logger.warning("A game with the same teams already exists: " + homeTeam + " - " + awayTeam);
            return;
        }
		
		FootballMatch match = new FootballMatch(homeTeam.trim(), awayTeam.trim());
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
    
    public void finishGame(String homeTeam, String awayTeam) {
    	Iterator<FootballMatch> iterator = matches.iterator();
        while (iterator.hasNext()) {
            FootballMatch match = iterator.next();
            if (match.getHomeTeam().equalsIgnoreCase(homeTeam) && match.getAwayTeam().equalsIgnoreCase(awayTeam)) {
                iterator.remove();
                break;
            }
        }	
	}
    
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }

        boolean matchExists = false;
        for (FootballMatch match : matches) {
            if (match.getHomeTeam().equalsIgnoreCase(homeTeam) && match.getAwayTeam().equalsIgnoreCase(awayTeam)) {
                match.updateScore(homeScore, awayScore);
                matchExists = true;
                break;
            }
        }

        if (!matchExists) {
            throw new IllegalArgumentException("Match does not exist for the provided teams.");
        }
    }
    
    
	public List<FootballMatch> getSummary() {
	    return matches;
	}

	public List<String> getSummaryOrderedByTotalScore() {
		
        List<FootballMatch> orderedMatches = new ArrayList<>(matches);
       
        orderedMatches = matches.stream()
                .sorted(Comparator.comparingInt(matches::indexOf).reversed())
                .sorted((match1, match2) -> {
                    int totalScore1 = match1.getHomeScore() + match1.getAwayScore();
                    int totalScore2 = match2.getHomeScore() + match2.getAwayScore();
                    return Integer.compare(totalScore2, totalScore1);
                })
                .collect(Collectors.toList());
               
        List<String> formattedSummary = new ArrayList<>();
        for (FootballMatch match : orderedMatches) {
            String summary = match.getHomeTeam() + " " + match.getHomeScore() + " - " + match.getAwayTeam() + " " + match.getAwayScore();
            formattedSummary.add(summary);
        }
        
        return formattedSummary;
    }
}
