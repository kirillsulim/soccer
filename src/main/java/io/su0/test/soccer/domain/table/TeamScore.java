package io.su0.test.soccer.domain.table;

public class TeamScore {

    private final String teamName;

    private int winCount;
    private int drawCount;
    private int looseCount;
    private int scored;
    private int missed;

    public TeamScore(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public int getLooseCount() {
        return looseCount;
    }

    public void setLooseCount(int looseCount) {
        this.looseCount = looseCount;
    }

    public int getScored() {
        return scored;
    }

    public void setScored(int scored) {
        this.scored = scored;
    }

    public int getMissed() {
        return missed;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }

    public int getPoints() {
        return this.winCount * 3 + this.drawCount;
    }
}
