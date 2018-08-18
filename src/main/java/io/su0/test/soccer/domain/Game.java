package io.su0.test.soccer.domain;

public class Game {

    private String team1;

    private String team2;

    private int team1Scores;

    private int team2Scores;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getTeam1Scores() {
        return team1Scores;
    }

    public void setTeam1Scores(int team1Scores) {
        this.team1Scores = team1Scores;
    }

    public int getTeam2Scores() {
        return team2Scores;
    }

    public void setTeam2Scores(int team2Scores) {
        this.team2Scores = team2Scores;
    }
}
