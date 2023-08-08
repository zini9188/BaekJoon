import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] board;
    static boolean[][] isBlack;
    static boolean[][] isBishop;
    static int[] dx = {-1, -1};
    static int[] dy = {-1, 1};
    static int N;
    static int black;
    static int white;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        isBlack = new boolean[N][N];
        isBishop = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
                isBlack[i][j] = i % 2 != 0 && j % 2 != 0 || i % 2 == 0 && j % 2 == 0;
            }
        }
        blackBoard(-1, 0);
        whiteBoard(-1, 0);
        bw.write(black + white + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void whiteBoard(int index, int count) {
        for (int i = index + 1; i < N * N; i++) {
            int x = i / N;
            int y = i % N;
            if (!isBlack[x][y] && board[x][y] == 1 && check(x, y)) {
                isBishop[x][y] = true;
                whiteBoard(i, count + 1);
                isBishop[x][y] = false;
            }
        }
        white = Math.max(white, count);
    }

    private static void blackBoard(int index, int count) {
        for (int i = index + 1; i < N * N; i++) {
            int x = i / N;
            int y = i % N;
            if (isBlack[x][y] && board[x][y] == 1 && check(x, y)) {
                isBishop[x][y] = true;
                blackBoard(i, count + 1);
                isBishop[x][y] = false;
            }
        }
        black = Math.max(black, count);
    }

    private static boolean check(int x, int y) {
        for (int i = 0; i < 2; i++) {
            int nx = x;
            int ny = y;

            while (isInRange(nx, ny)) {
                if (isBishop[nx][ny]) {
                    return false;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
        return true;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}