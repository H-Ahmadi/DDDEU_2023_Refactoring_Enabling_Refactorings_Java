package dddeurope.workshops.services;

import dddeurope.workshops.dataAccess.GroupStageDataAccess;
import dddeurope.workshops.dataAccess.TeamDataAccess;
import dddeurope.workshops.model.*;
import dddeurope.workshops.model.enums.Stages;

public class GameService {
    public static void addGame(AddGameDto dto) {
        // Update the group
        GroupStage group = GroupStageDataAccess.loadGroupStage(dto.getGroupId());
        Team homeTeam = TeamDataAccess.loadTeam(dto.getHomeTeamId());
        Team awayTeam = TeamDataAccess.loadTeam(dto.getAwayTeamId());
        Game game = new Game(homeTeam, awayTeam);
        game.playGame(dto.getHomeScores(), dto.getAwayScores());

        group.getGames().add(game);
        GroupStageDataAccess.updateGroup(group);

        // Update FIFA ranking
        FifaRankingService rankingService = new FifaRankingService();
        rankingService.updateRankingForTeams(game, Stages.Group);
    }
}

