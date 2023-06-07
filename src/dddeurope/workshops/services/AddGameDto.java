package dddeurope.workshops.services;

public class AddGameDto {
    private long groupId;
    private long homeTeamId;
    private long awayTeamId;
    private int homeScores;
    private int awayScores;

    public long getGroupId() {
        return groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getHomeTeamId() {
        return homeTeamId;
    }
    public void setHomeTeamId(long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public long getAwayTeamId() {
        return awayTeamId;
    }
    public void setAwayTeamId(long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeScores() {
        return homeScores;
    }
    public void setHomeScores(int homeScores) {
        this.homeScores = homeScores;
    }

    public int getAwayScores() {
        return awayScores;
    }
    public void setAwayScores(int awayScores) {
        this.awayScores = awayScores;
    }
}
