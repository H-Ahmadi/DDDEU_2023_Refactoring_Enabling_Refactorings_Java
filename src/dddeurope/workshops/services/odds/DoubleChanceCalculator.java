package dddeurope.workshops.services.odds;

import dddeurope.workshops.core.RefactoringViolationException;
import dddeurope.workshops.model.Game;
import dddeurope.workshops.model.odds.DoubleChanceOdds;
import dddeurope.workshops.model.odds.HomeDrawAwayOdds;
import dddeurope.workshops.model.odds.Odds;

public class DoubleChanceCalculator implements OddsCalculator {

    @Override
    public Odds calculateOdds(Game game) {
        HomeDrawAwayOddsCalculator oddsCalculator = new HomeDrawAwayOddsCalculator();
        HomeDrawAwayOdds odds = (HomeDrawAwayOdds) oddsCalculator.calculateOdds(game);

        double doubleChanceRate = getDoubleChanceRateFromConfigFile();

        return new DoubleChanceOdds(
                (odds.getHome() + odds.getDraw()) / doubleChanceRate,
                (odds.getAway() + odds.getDraw()) / doubleChanceRate
        );
    }

    private double getDoubleChanceRateFromConfigFile() {
        throw new RefactoringViolationException();
    }
}

