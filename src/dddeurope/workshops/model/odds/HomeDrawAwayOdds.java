package dddeurope.workshops.model.odds;

public class HomeDrawAwayOdds extends Odds {
    private double home;
    private double draw;
    private double away;

    public double getHome() {
        return home;
    }
    public void setHome(double home) {
        this.home = home;
    }

    public double getDraw() {
        return draw;
    }
    public void setDraw(double draw) {
        this.draw = draw;
    }

    public double getAway() {
        return away;
    }
    public void setAway(double away) {
        this.away = away;
    }
}
