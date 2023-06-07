package dddeurope.workshops.model;

import java.util.UUID;

public class Game {
    private UUID id;
    private Team team1;
    private Team team2;
    private int score1;
    private int score2;

    public Game(Team team1, Team team2) {
        this.id = UUID.randomUUID();
        this.team1 = team1;
        this.team2 = team2;
    }

    public UUID getId() { return id; }

    public Team getTeam1() { return team1; }
    public Team getTeam2() {
        return team2;
    }

    public int getScore1() {
        return score1;
    }
    public int getScore2() {
        return score2;
    }

    public void playGame(int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;
    }
}
