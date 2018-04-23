package gna;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int[][] tiles;
    private int gapRow;
    private int gapColumn;


    // construct a board from an N-by-N array of tiles
    public Board(int[][] tiles) {
        this.tiles = deepCopyOfMatrix(tiles);
    }

    // return number of blocks out of place
    public int hamming() {
        int counter = 0;
        int k = 0;
        for (int[] tile : tiles) {
            for (int aTile : tile) {
                if (k != aTile - 1 && aTile != 0) counter++;
                k++;
            }
        }
        return counter;
    }


    // return sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int counter = 0;
        int k = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (k != tiles[i][j] - 1  && tiles[i][j] != 0) {
                    counter += distanceFromGoal(tiles[i][j], i, j);
                }
                k++;
            }
        }
        return counter;
    }

    // Does this board equal y. Two boards are equal when they both were constructed
    // using tiles[][] arrays that contained the same values.
    @Override
    public boolean equals(Object y) {
        if (!(y instanceof Board))
            return false;

        Board other = (Board) y;
        return Arrays.deepEquals(tiles, other.tiles);
    }

    // Since we override equals(), we must also override hashCode(). When two objects are
    // equal according to equals() they must return the same hashCode. More info:
    // - http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java/27609#27609
    // - http://www.ibm.com/developerworks/library/j-jtp05273/
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }


    // return a Collection of all neighboring board positions
    public Collection<Board> neighbors() {
        int row = gapRow;
        int column = gapColumn;
        List<Board> neighbors = new ArrayList<>();

        if (isInbound(row - 1, column)) {
            Board board = new Board(tiles);
            board.upSwitch(row, column);
            neighbors.add(board);
        }
        if (isInbound(row + 1, column)) {
            Board board = new Board(tiles);
            board.downSwitch(row, column);
            neighbors.add(board);
        }

        if (isInbound(row, column - 1)) {
            Board board = new Board(tiles);
            board.leftSwitch(row, column);
            neighbors.add(board);
        }

        if (isInbound(row, column + 1)) {
            Board board = new Board(tiles);
            board.rightSwitch(row, column);
            neighbors.add(board);
        }
        return neighbors;
    }

    // return a string representation of the board
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int[] tile : tiles) {
            str.append(Arrays.toString(tile)).append("\n");
        }
        return str.toString();
    }

    // is the initial board solvable? Note that the empty tile must
    // first be moved to its correct position.
    public boolean isSolvable() {
        Board board = new Board(tiles);
        List<Integer> lst = board.listOfMatrix();
        board.moveZeroDownRightCorner();
        double numerator = 1;
        double denominator = 1;
        for (int k = 0; k < lst.size(); k++) {
            int j = lst.get(k);
            int i = 1;
            while(i < j){
                numerator *= (lst.indexOf(j) + 1) - (lst.indexOf(i) + 1);
                denominator *= j - i;
                i++;
            }
        }
        return numerator/denominator >= 0;
    }

    public boolean isGoal(){
        return hamming() == 0;
    }

    /**
     * @param matrix the given matrix
     * @return a deepCopy of the given matrix
     */
    private int[][] deepCopyOfMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0){
                    gapRow = i;
                    gapColumn = j;
                }
                result[i][j] = matrix[i][j];
            }
        }
        return result;
    }

    public List<Integer> listOfMatrix(){
        List<Integer> lst = new ArrayList<>();
        for (int[] tile : tiles) {
            for (int aTile : tile) {
                lst.add(aTile);
            }
        }
        return lst;
    }


    /**
     * @return the horizontal + vertical distance of the givenTile from its original position to its correct position
     */
    private int distanceFromGoal(int givenTile, int row, int column) {
        int destinationRow = Math.floorDiv(givenTile - 1, tiles.length);
        int destinationColumn = Math.floorMod(givenTile - 1, tiles.length);
        int columnDistance = Math.abs(destinationColumn - column);
        int rowDistance = Math.abs(destinationRow - row);
        return columnDistance + rowDistance;
    }

    //Move the zero to the down right corner by using the rightSwitch() and downSwitch() functions
    public void moveZeroDownRightCorner(){
        int row = gapRow;
        int column = gapColumn;

        while (column < tiles[0].length - 1){
            rightSwitch(row, column);
            column++;
        }

        while (row < tiles.length - 1){
            downSwitch(row, column);
            row++;
        }
    }

    /**
     * @return true if the given location is still in bounds
     */
    private boolean isInbound(int row, int column) {
        return (row >= 0 && row < tiles.length) && (column >= 0 && column < tiles[row].length);
    }

    private void rightSwitch(int row, int column) {
        tiles[row][column] = tiles[row][column + 1];
        tiles[row][column + 1] = 0;
        gapColumn += 1;
    }

    private void leftSwitch(int row, int column) {
        tiles[row][column] = tiles[row][column - 1];
        tiles[row][column - 1] = 0;
        gapColumn -= 1;
    }

    private void downSwitch(int row, int column) {
        tiles[row][column] = tiles[row + 1][column];
        tiles[row + 1][column] = 0;
        gapRow += 1;
    }

    private void upSwitch(int row, int column) {
        tiles[row][column] = tiles[row - 1][column];
        tiles[row - 1][column] = 0;
        gapRow -= 1;

    }

    private List<Integer> lstSmallerItems(List<Integer> lst, int value){
        List<Integer> newLst = new ArrayList<>();
        for (Integer aLst : lst) {
            if (aLst < value && aLst != 0) newLst.add(aLst);
        }
        return newLst;
    }

    public int[][] getTiles() {
        return tiles;
    }
}

