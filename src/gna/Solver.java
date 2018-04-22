package gna;

import java.util.*;

import libpract.PriorityFunc;

public class Solver
{

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
		    comparator = Comparator.comparingInt(o -> (o.getCurrConfiguration().hamming() + o.getSteps()));

		} else if (priority == PriorityFunc.MANHATTAN) {
            comparator = Comparator.comparingInt(o -> (o.getCurrConfiguration().manhattan() + o.getSteps()));

		} else {
			throw new IllegalArgumentException("Priority function not supported");
		}

        PriorityQueue<State> queue = new PriorityQueue<>(comparator);
        solve(initial, queue);

        }
	

	/**
	 * Returns a List of board positions as the solution. It should contain the initial
	 * Board as well as the solution (if these are equal only one Board is returned).
	 */
	public List<Board> solution()
	{
	    return solutionMethod;
	}

	public void solutionToString(){
        for (int i = 0; i < solutionMethod.size(); i++) {
            System.out.println(solutionMethod.get(i) + "\n");
        }
    }

    public int[][] solutionBoard(Board board){
        int[][] tiles = new int[board.getTiles().length][board.getTiles()[0].length];
        int k = 1;
        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles()[i].length; j++) {
                tiles[i][j] = k;
                k++;
            }
        }
        tiles[board.getTiles().length-1][board.getTiles()[0].length - 1] = 0;
        return tiles;
    }

    private int solve(Board initial, PriorityQueue<State> queue){
        int currSteps = 0;
        State currState = new State(initial, null, 0);
        queue.add(currState);
        int[][] result = solutionBoard(initial);

        while (! currState.getCurrConfiguration().isEqualBoard(result)){
            currState = queue.peek();
            solutionMethod.add(currState.getCurrConfiguration());
            currSteps++;
            queue.remove();
            List<Board> neighbors = (List<Board>) currState.getCurrConfiguration().neighbors();
            for (Board currNeighbor: neighbors){
                queue.add(new State(currNeighbor, currState.getCurrConfiguration(), currSteps));
            }
        }
        return currSteps;
    }
}



