package dddeurope.workshops.views;

import dddeurope.workshops.dataAccess.GroupStageDataAccess;
import dddeurope.workshops.model.Game;
import dddeurope.workshops.model.GroupStage;
import dddeurope.workshops.model.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupStageView {
    public void showGroup(long groupStageId) {
        GroupStage groupStage = GroupStageDataAccess.loadGroupStage(groupStageId);

        System.out.println("+----------------+------+------+------+------+------+------+-------------+");
        System.out.println("|      Team      |   P  |   W  |   D  |   L  |  GD  |  GS  |   Points    |");
        System.out.println("+----------------+------+------+------+------+------+------+-------------+");

        Map<Long, StageTableViewModel> tableRows = new HashMap<>();
        for (Team team : groupStage.getTeams()) {
            tableRows.put(team.getId(), new StageTableViewModel(team.getName()));
        }

        for (Game game : groupStage.getGames()) {
            Team team1 = game.getTeam1();
            Team team2 = game.getTeam2();
            int score1 = game.getScore1();
            int score2 = game.getScore2();

            tableRows.get(team1.getId()).incrementGamesPlayed();
            tableRows.get(team2.getId()).incrementGamesPlayed();

            tableRows.get(team1.getId()).incrementGoalScored(score1);
            tableRows.get(team1.getId()).updateGoalDifference(score1 - score2);
            if (score1 > score2) {
                tableRows.get(team1.getId()).incrementPoints(3);
                tableRows.get(team1.getId()).incrementWin();
                tableRows.get(team2.getId()).incrementLost();
            } else if (score1 == score2) {
                tableRows.get(team1.getId()).incrementPoints(1);
                tableRows.get(team1.getId()).incrementDraw();
            }

            tableRows.get(team2.getId()).incrementGoalScored(score2);
            tableRows.get(team2.getId()).updateGoalDifference(score2 - score1);
            if (score2 > score1) {
                tableRows.get(team2.getId()).incrementPoints(3);
                tableRows.get(team2.getId()).incrementWin();
                tableRows.get(team1.getId()).incrementLost();
            } else if (score2 == score1) {
                tableRows.get(team2.getId()).incrementPoints(1);
                tableRows.get(team2.getId()).incrementDraw();
            }
        }

        List<Map.Entry<Long, StageTableViewModel>> rows = new ArrayList<>(tableRows.entrySet());
        rows.sort((entry1, entry2) -> {
            StageTableViewModel team1 = entry1.getValue();
            StageTableViewModel team2 = entry2.getValue();
            if (team1.getPoints() != team2.getPoints())
                return Integer.compare(team2.getPoints(), team1.getPoints());
            else if (team1.getGoalDifference() != team2.getGoalDifference())
                return Integer.compare(team2.getGoalDifference(), team1.getGoalDifference());
            else
                return Integer.compare(team2.getGoalScored(), team1.getGoalScored());
        });

        for (Map.Entry<Long, StageTableViewModel> row : rows) {
            StageTableViewModel team = row.getValue();
            System.out.printf("|%16s|%6s|%6s|%6s|%6s|%6s|%6s|%13s|%n",
                    padCenter(team.getTeamName(), 16),
                    padCenter(String.valueOf(team.getGamesPlayed()), 6),
                    padCenter(String.valueOf(team.getWin()), 6),
                    padCenter(String.valueOf(team.getDraw()), 6),
                    padCenter(String.valueOf(team.getLost()), 6),
                    padCenter(String.valueOf(team.getGoalDifference()), 6),
                    padCenter(String.valueOf(team.getGoalScored()), 6),
                    padCenter(String.valueOf(team.getPoints()), 13)
            );
        }
        System.out.println("+----------------+------+------+------+------+------+------+-------------+");
    }

    private String padCenter(String text, int newWidth) {
        final char filler = ' ';
        int length = text.length();
        int charactersToPad = newWidth - length;
        if (charactersToPad < 0) throw new IllegalArgumentException("New width must be greater than string length.");
        int padLeft = charactersToPad / 2 + charactersToPad % 2;
        //add a space to the left if the string is an odd number
        int padRight = charactersToPad / 2;

        StringBuilder resultBuilder = new StringBuilder(newWidth);
        for (int i = 0; i < padLeft; i++) resultBuilder.insert(i, filler);
        for (int i = 0; i < length; i++) resultBuilder.insert(i + padLeft, text.charAt(i));
        for (int i = newWidth - padRight; i < newWidth; i++) resultBuilder.insert(i, filler);
        return resultBuilder.toString();
    }

    public static class StageTableViewModel {
        private final String teamName;
        private int gamesPlayed;
        private int win;
        private int draw;
        private int lost;
        private int goalDifference;
        private int goalScored;
        private int points;

        public StageTableViewModel(String teamName) {
            this.teamName = teamName;
        }

        public String getTeamName() {
            return teamName;
        }

        public int getGamesPlayed() {
            return gamesPlayed;
        }

        public void incrementGamesPlayed() {
            gamesPlayed++;
        }

        public int getWin() {
            return win;
        }

        public void incrementWin() {
            win++;
        }

        public int getDraw() {
            return draw;
        }

        public void incrementDraw() {
            draw++;
        }

        public int getLost() {
            return lost;
        }

        public void incrementLost() {
            lost++;
        }

        public int getGoalDifference() {
            return goalDifference;
        }

        public void updateGoalDifference(int difference) {
            goalDifference += difference;
        }

        public int getGoalScored() {
            return goalScored;
        }

        public void incrementGoalScored(int goals) {
            goalScored += goals;
        }

        public int getPoints() {
            return points;
        }

        public void incrementPoints(int points) {
            this.points += points;
        }
    }
}

