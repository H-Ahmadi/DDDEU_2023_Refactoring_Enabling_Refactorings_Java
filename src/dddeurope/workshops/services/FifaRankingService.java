package dddeurope.workshops.services;

import dddeurope.workshops.dataAccess.RankingDataAccess;
import dddeurope.workshops.model.Game;
import dddeurope.workshops.model.Team;
import dddeurope.workshops.model.enums.Confederations;
import dddeurope.workshops.model.enums.Stages;

public class FifaRankingService {
    public void updateRankingForTeams(Game game, Stages stage) {
        updateRankForTeam(game.getTeam1(), game.getTeam2(), game.getScore1(), game.getScore2(), stage);
        updateRankForTeam(game.getTeam2(), game.getTeam1(), game.getScore2(), game.getScore1(), stage);
    }

    private void updateRankForTeam(Team team, Team opponent, int goalsScored, int goalsAgainst, Stages stage) {
        double matchResultPoints = 0.0;
        double opponentStrengthPoints = 0.0;
        double matchImportancePoints = 0.0;
        double confederationWeighting = 0.0;

        if (goalsScored > goalsAgainst) {
            matchResultPoints += 2.0;
        } else if (goalsScored == goalsAgainst) {
            matchResultPoints += 1.0;
        }

        if (stage == Stages.Group) {
            matchImportancePoints += 0.5;
        } else if (stage == Stages.Knockout) {
            matchImportancePoints += 1.0;
        } else if (stage == Stages.Final) {
            matchImportancePoints += 2.0;
        }

        Confederations confederation = ConfederationService.findConfederationForTeam(team.getId());

        switch (confederation) {
            case AFC:
                confederationWeighting = 1.0;
                break;
            case CAF:
                confederationWeighting = 0.9;
                break;
            case CONCACAF:
                confederationWeighting = 0.8;
                break;
            case CONMEBOL:
                confederationWeighting = 1.2;
                break;
            case OFC:
                confederationWeighting = 0.7;
                break;
            case UEFA:
                confederationWeighting = 1.5;
                break;
            default:
                confederationWeighting = 1.0;
                break;
        }

        double opponentRanking = RankingDataAccess.getTeamRanking(opponent.getId());
        if (opponentRanking <= 100) {
            opponentStrengthPoints = 1.0;
        } else if (opponentRanking <= 200) {
            opponentStrengthPoints = 1.1;
        } else if (opponentRanking <= 300) {
            opponentStrengthPoints = 1.2;
        } else if (opponentRanking <= 400) {
            opponentStrengthPoints = 1.3;
        } else if (opponentRanking <= 500) {
            opponentStrengthPoints = 1.4;
        } else {
            opponentStrengthPoints = 1.5;
        }

        double rankingPoints = matchResultPoints + opponentStrengthPoints + matchImportancePoints;
        rankingPoints *= confederationWeighting;

        RankingDataAccess.updateRankForTeam(team.getId(), rankingPoints);
    }
}

