package com.sportradar.scoreboard;

public class FootballMatch {

	 private String homeTeam;
	 private String awayTeam;
	 private int homeScore;
	 private int awayScore;
	 
	 public FootballMatch(String homeTeam, String awayTeam) {
		 this.homeTeam = homeTeam;
	     this.awayTeam = awayTeam;
	     this.homeScore = 0;
	     this.awayScore = 0;
	 }
	 
	 public void updateScore(int homeScore, int awayScore) {
		 this.homeScore = homeScore;
		 this.awayScore = awayScore;
	 }

	 public int getTotalScore() {
		 return homeScore + awayScore;
	 }
 
	 public String getHomeTeam() {
		 return homeTeam;
	 }
	 
	 public String getAwayTeam() {
		 return awayTeam;
	 }
	 
	 public int getHomeScore() {
		 return homeScore;
	 }
	 
	 public int getAwayScore() {
		 return awayScore;
	 }
	
	 @Override
	 public String toString() {
		 return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
	 }
	
}
