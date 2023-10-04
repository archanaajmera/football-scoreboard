package com.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

private List<FootballMatch> matches;
	
	public ScoreBoard() {
        this.matches = new ArrayList<>();
    }
		
	public void startGame(String homeTeam, String awayTeam) {
		// TODO : Implementation here
		
	}
	
	public List<FootballMatch> getSummary() {
	    return matches;
	}
	
}
