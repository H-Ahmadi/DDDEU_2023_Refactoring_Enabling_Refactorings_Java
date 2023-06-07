package dddeurope.workshops.views;

import dddeurope.workshops.dataAccess.GameDataAccess;
import dddeurope.workshops.model.Game;
import dddeurope.workshops.model.odds.DoubleChanceOdds;
import dddeurope.workshops.model.odds.HomeDrawAwayOdds;
import dddeurope.workshops.model.odds.Odds;
import dddeurope.workshops.model.odds.OddsType;
import dddeurope.workshops.services.odds.DoubleChanceCalculator;
import dddeurope.workshops.services.odds.HomeDrawAwayOddsCalculator;
import dddeurope.workshops.services.odds.OddsCalculator;

import java.util.HashMap;
import java.util.Map;

public class OddsView {
    public void showOddsForGame(long id, OddsType oddsType) {
        Game game = GameDataAccess.retrieve(id);

        OddsCalculator oddsCalculator = new HomeDrawAwayOddsCalculator();
        if (oddsType == OddsType.DOUBLE_CHANCE)
            oddsCalculator = new DoubleChanceCalculator();

        Odds odds = oddsCalculator.calculateOdds(game);
        Map<String, Double> tableRows = new HashMap<>();
        if (odds instanceof HomeDrawAwayOdds) {
            HomeDrawAwayOdds data = (HomeDrawAwayOdds) odds;
            tableRows.put("Home", data.getHome());
            tableRows.put("Draw", data.getDraw());
            tableRows.put("Away", data.getAway());
        } else if (odds instanceof DoubleChanceOdds) {
            DoubleChanceOdds doubleChanceData = (DoubleChanceOdds) odds;
            tableRows.put("Home Or Draw", doubleChanceData.getHomeOrDraw());
            tableRows.put("Away Or Draw", doubleChanceData.getAwayOrDraw());
        }

        System.out.println("+-------------------------------------+------------+");
        System.out.println("|                 Odds                +    Rate    +");
        System.out.println("+-------------------------------------+------------+");
        for (Map.Entry<String, Double> tableRow : tableRows.entrySet()) {
            System.out.printf("|%s+%s+ %n", padCenter(tableRow.getKey(), 37), padCenter(String.format("%.2f", tableRow.getValue()), 12));
        }
        System.out.println("+-------------------------------------+------------+");
    }

    private static String padCenter(String text, int newWidth) {
        char filler = ' ';
        int length = text.length();
        int charactersToPad = newWidth - length;
        if (charactersToPad < 0)
            throw new IllegalArgumentException("New width must be greater than string length.");
        int padLeft = charactersToPad / 2 + charactersToPad % 2;
        int padRight = charactersToPad / 2;

        StringBuilder resultBuilder = new StringBuilder(newWidth);
        for (int i = 0; i < padLeft; i++)
            resultBuilder.insert(i, filler);
        for (int i = 0; i < length; i++)
            resultBuilder.insert(i + padLeft, text.charAt(i));
        for (int i = newWidth - padRight; i < newWidth; i++)
            resultBuilder.insert(i, filler);
        return resultBuilder.toString();
    }
}



