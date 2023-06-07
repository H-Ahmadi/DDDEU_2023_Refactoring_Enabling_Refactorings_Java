package dddeurope.workshops.services.odds;

import dddeurope.workshops.dataAccess.RankingDataAccess;
import dddeurope.workshops.model.Game;
import dddeurope.workshops.model.odds.HomeDrawAwayOdds;
import dddeurope.workshops.model.odds.Odds;

public class HomeDrawAwayOddsCalculator implements OddsCalculator {
    @Override
    public Odds calculateOdds(Game game) {
        double homeTeamRanking = RankingDataAccess.getTeamRanking(game.getTeam1().getId());
        double awayTeamRanking = RankingDataAccess.getTeamRanking(game.getTeam2().getId());
        var odds = new HomeDrawAwayOdds();
        odds.setHome(awayTeamRanking / homeTeamRanking + 1);
        odds.setAway(homeTeamRanking / awayTeamRanking + 1);
        odds.setDraw((odds.getHome() + odds.getAway()) / 2);
        return odds;
    }
}