package gna;

import java.util.*;

import libpract.PriorityFunc;

public class Solver
{
    private int steps;
    private int generatedStates;

	public List<Board> solutionMethod;
	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public Solver(Board initial, PriorityFunc priority)
	{
		// Use the given priority function (either PriorityFunc.HAMMING
		// or PriorityFunc.MANHATTAN) to solve the puzzle.
        solutionMethod = new ArrayList<>();
        Comparator<State> comparator;
        if (priority == PriorityFunc.HAMMING) {
		    comparator = Comparator.comparingInt(o -> (o.getConfiguration().hamming() + o.getSteps()));

		} else if (priority == PriorityFunc.MANHATTAN) {
            comparator = Comparator.comparingInt(o -> (o.getConfiguration().manhattan() + o.getSteps()));

		} else {
			throw new IllegalArgumentException("Priority function not supported");
		}

        PriorityQueue<State> queue = new PriorityQueue<>(comparator);
        solve(initial, queue);
        }

    private void solve(Board initial, PriorityQueue<State> queue){
        State currState = new State(initial, 0, null);
        queue.add(currState);
        generatedStates = 1;
        steps = 0;


        while (! currState.getConfiguration().isGoal()){
            currState = queue.remove();
            for (Board currNeighbor: currState.getConfiguration().neighbors()){
                if (currState.getPreviousState() == null || ! currNeighbor.equals(currState.getPreviousState().getConfiguration())){
                    queue.add(new State(currNeighbor, currState.getSteps() + 1, currState));
                    generatedStates++;
                }
            }
        }
        while (currState != null){
            solutionMethod.add(0, currState.getConfiguration());
            currState = currState.getPreviousState();
            steps = solutionMethod.size() - 1;
        }
    }

	/**
	 * Returns a List of board positions as the solution. It should contain the initial
	 * Board as well as the solution (if these are equal only one Board is returned).
	 */
	public List<Board> solution()
	{
	    return solutionMethod;
	}

    public int getSteps() {
        return steps;
    }

    public int getGeneratedStates() {
        return generatedStates;
    }
}



