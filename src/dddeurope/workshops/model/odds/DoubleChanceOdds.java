package dddeurope.workshops.model.odds;

public class DoubleChanceOdds extends Odds {
    private double homeOrDraw;
    private double awayOrDraw;
    public DoubleChanceOdds(double homeOrDraw, double awayOrDraw) {
        this.homeOrDraw = homeOrDraw;
        this.awayOrDraw = awayOrDraw;
    }
    public double getHomeOrDraw() {
        return homeOrDraw;
    }
    public void setHomeOrDraw(double homeOrDraw) {
        this.homeOrDraw = homeOrDraw;
    }

    public double getAwayOrDraw() {
        return awayOrDraw;
    }
    public void setAwayOrDraw(double awayOrDraw) {
        this.awayOrDraw = awayOrDraw;
    }
}
