import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] row;
    static boolean[][] col;
    static boolean[][] square;
    static int[][] board;
    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        board = new int[9][9];
        square = new boolean[9][10];
        row = new boolean[9][10];
        col = new boolean[9][10];

        for (int x = 0; x < 9; x++) {
            String line = br.readLine();
            for (int y = 0; y < 9; y++) {
                board[x][y] = line.charAt(y) - '0';
                row[x][board[x][y]] = true;
                col[y][board[x][y]] = true;
                square[getSquare(x, y)][board[x][y]] = true;
            }
        }

        sudoku(0);
    }

    private static void sudoku(int num) {
        if (num == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    builder.append(board[i][j]);
                }
                builder.append("\n");
            }
            System.out.println(builder.toString());
            System.exit(0);
        }

        int x = num / 9;
        int y = num % 9;
        if (board[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (!row[x][i] && !col[y][i] && !square[getSquare(x, y)][i]) {
                    row[x][i] = true;
                    col[y][i] = true;
                    square[getSquare(x, y)][i] = true;
                    board[x][y] = i;
                    sudoku(num + 1);
                    row[x][i] = false;
                    col[y][i] = false;
                    square[getSquare(x, y)][i] = false;
                    board[x][y] = 0;
                }
            }
        } else {
            sudoku(num + 1);
        }
    }

    private static int getSquare(int x, int y) {
        return (x / 3) * 3 + (y / 3);
    }
}