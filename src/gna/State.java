package gna;

public class State {
    private Board configuration;
    private int steps;
    private State previousState;
    private int ranking;

    public State(Board configuration, int steps, State previousState) {
        this.configuration = configuration;
        this.steps = steps;
        this.previousState = previousState;
        this.ranking = configuration.hamming() + steps;
    }

    public Board getConfiguration() {
        return configuration;
    }

    public int getSteps() {
        return steps;
    }

    public State getPreviousState() {
        return previousState;
    }

    public int getRanking() {
        return ranking;
    }
}
