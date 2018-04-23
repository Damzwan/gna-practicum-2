package gna;

public class petitTest {
    public static void main(String[] args) {
        Board board1 = new Board(new int[][]{{0, 15, 14, 13}, {12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}});
        long startTime = System.nanoTime();
        board1.manhattan();
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
