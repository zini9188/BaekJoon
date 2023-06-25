import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = new int[9][9];
    static boolean[][] rowState = new boolean[9][10];
    static boolean[][] colState = new boolean[9][10];
    static boolean[][] squareState = new boolean[9][10];
    static ArrayList<Cell> emptyCell = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer;        
        for (int x = 0; x < 9; x++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int y = 0; y < 9; y++) {
                board[x][y] = Integer.parseInt(tokenizer.nextToken());
                if (board[x][y] == 0) {
                    emptyCell.add(new Cell(x, y));
                    continue;
                }
                rowState[x][board[x][y]] = true;
                colState[y][board[x][y]] = true;
                squareState[getSquarePosition(x, y)][board[x][y]] = true;
            }
        }

        dfs(0, 0, emptyCell.size());

        bw.close();
        br.close();
    }

    private static void dfs(int index, int count, int size) {
        if (count == size) {
            printBoard();
            System.exit(0);
        }

        Cell cell = emptyCell.get(index);
        for (int number = 1; number <= 9; number++) {
            if (!isContainedInRow(cell, number) && !isContainedInCol(cell, number) && !isContainedInSquare(cell, number)) {
                rowState[cell.x][number] = true;
                colState[cell.y][number] = true;
                squareState[getSquarePosition(cell)][number] = true;
                board[cell.x][cell.y] = number;
                dfs(index + 1, count + 1, size);
                rowState[cell.x][number] = false;
                colState[cell.y][number] = false;
                squareState[getSquarePosition(cell)][number] = false;
                board[cell.x][cell.y] = 0;
            }
        }
    }

    private static boolean isContainedInSquare(Cell cell, int number) {
        return squareState[getSquarePosition(cell)][number];
    }

    private static boolean isContainedInCol(Cell cell, int number) {
        return colState[cell.y][number];
    }

    private static boolean isContainedInRow(Cell cell, int number) {
        return rowState[cell.x][number];
    }

    private static int getSquarePosition(Cell cell) {
        return (cell.x / 3) * 3 + cell.y / 3;
    }

    private static int getSquarePosition(int x, int y) {
        return (x / 3) * 3 + y / 3;
    }

    private static void printBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }

    static class Cell {
        int x, y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}