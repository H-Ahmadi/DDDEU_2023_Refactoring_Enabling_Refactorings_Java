package dddeurope.workshops.model;

import java.util.ArrayList;
import java.util.List;

public class GroupStage {
    private long id;
    private String name;
    private List<Team> teams;
    private List<Game> games;

    public GroupStage() {
        teams = new ArrayList<>();
        games = new ArrayList<>();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Team> getTeams() { return teams; }
    public void setTeams(List<Team> teams) { this.teams = teams; }

    public List<Game> getGames() { return games; }
    public void setGames(List<Game> games) { this.games = games;  }
}
