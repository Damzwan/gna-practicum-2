package gna;

import libpract.PriorityFunc;

public class PetitTest {
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{1, 2, 3, 4, 5}, {12, 6, 8, 9, 10}, {0, 7, 13, 19, 14}, {11, 16, 17, 18, 15}, {21, 22, 23, 24, 20}});
        Board board1 = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
        Board board2 = new Board(new int[][]{{1, 2, 3, 4, 5, 7, 14}, {8, 9, 10, 11, 12, 13, 6}, {15, 16, 17, 18, 19, 20, 21},
                {22, 23, 24, 25, 26, 27, 28}, {29, 30, 31, 32, 0, 33, 34}, {36, 37, 38, 39, 40, 41, 35}, {43, 44, 45, 46, 47, 48, 42}});
        System.out.println(board1.toString());

        Solver solver = new Solver(board1, PriorityFunc.HAMMING);
        solver.solutionToString();
    }
}
