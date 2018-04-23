package gna;

import java.util.Collection;
import java.util.List;

import libpract.PriorityFunc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A number of JUnit tests for Solver.
 * <p>
 * Feel free to modify these to automatically test puzzles or other functionality
 */
public class UnitTests {

    @Test
    public void testBoardConstructor() {
        Board board1 = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
        Board board2 = new Board(board1.getTiles());
        board1.getTiles()[0][0] = 5;
        assertEquals(0, board2.getTiles()[0][0]);
    }

    @Test
    public void testHamming() {
        Board board1 = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        assertEquals(5, board1.hamming());
        Board board2 = new Board(new int[][]{{1, 2, 3, 4, 5}, {12, 6, 8, 9, 10}, {0, 7, 13, 19, 14}, {11, 16, 17, 18, 15}, {21, 22, 23, 24, 20}});
        assertEquals(11, board2.hamming());
        Board board3 = new Board(new int[][]{{0, 4, 3}, {2, 1, 6}, {7, 5, 8}});
        assertEquals(5,board3.hamming());

    }

    @Test
    public void testManhattan() {
        Board board1 = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        assertEquals(10, board1.manhattan());
        Board board2 = new Board(new int[][]{{1, 2, 3, 4, 5}, {12, 6, 8, 9, 10}, {0, 7, 13, 19, 14}, {11, 16, 17, 18, 15}, {21, 22, 23, 24, 20}});
        assertEquals(12, board2.manhattan());
        Board board3 = new Board(new int[][]{{0, 4, 3}, {2, 1, 6}, {7, 5, 8}});
        assertEquals(8,board3.manhattan());
    }

    @Test
    public void testIsSolvable(){
        Board board1 = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
        assertEquals(true, board1.isSolvable());
        Board board2 = new Board(new int[][]{{1, 2, 3, 4, 5}, {12, 6, 8, 9, 10}, {0, 7, 13, 19, 14}, {11, 16, 17, 18, 15}, {21, 22, 23, 24, 20}});
        assertEquals(true, board2.isSolvable());
        Board board3 = new Board(new int[][]{{1, 2, 3}, {4, 6, 5}, {7, 8, 0}});
        assertEquals(false, board3.isSolvable());
        Board board4 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {8, 7, 0}});
        assertEquals(false, board4.isSolvable());
        Board board5 = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        assertEquals(true, board5.isSolvable());
    }

}
