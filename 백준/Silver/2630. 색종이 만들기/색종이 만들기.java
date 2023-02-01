import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int white = 0, blue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer;
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        solution();
        bw.write(white + "\n" + blue);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        divide(1, 1, N);
    }

    private static void divide(int x, int y, int n) {
        int flag = check(x, y, n);
        if (flag == 1) {
            blue++;
            return;
        }
        if (flag == 0) {
            white++;
            return;
        }
        divide(x, y, n / 2);
        divide(x, y + n / 2, n / 2);
        divide(x + n / 2, y, n / 2);
        divide(x + n / 2, y + n / 2, n / 2);
    }

    private static int check(int x, int y, int n) {
        int compare = board[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (board[i][j] != compare) {
                    return 2;
                }
            }
        }
        return compare;
    }
}