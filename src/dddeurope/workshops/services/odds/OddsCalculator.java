package dddeurope.workshops.services.odds;

import dddeurope.workshops.model.Game;
import dddeurope.workshops.model.odds.Odds;

public interface OddsCalculator {
    Odds calculateOdds(Game game);
}
