package gna;

public class State {
    private Board currConfiguration;
    private Board previousConfiguration;
    private int steps;

    public State(Board currConfiguration, Board previousConfiguration, int steps) {
        this.currConfiguration = currConfiguration;
        this.previousConfiguration = previousConfiguration;
        this.steps = steps;
    }

    public Board getCurrConfiguration() {
        return currConfiguration;
    }

    public void setCurrConfiguration(Board currConfiguration) {
        this.currConfiguration = currConfiguration;
    }

    public Board getPreviousConfiguration() {
        return previousConfiguration;
    }

    public void setPreviousConfiguration(Board previousConfiguration) {
        this.previousConfiguration = previousConfiguration;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
