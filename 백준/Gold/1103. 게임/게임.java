import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static boolean[][] visited;
    private static int[][] dp;
    private static int N, M;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == 'H') {
                    board[i][j] = 0;
                } else {
                    board[i][j] = input.charAt(j) - '0';
                }
            }
        }

        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        visited = new boolean[N][M];
        sb.append(dfs(0, 0));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M || board[x][y] == 0) {
            return 0;
        }

        if (visited[x][y]) {
            return -1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        visited[x][y] = true;
        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + (dx[i] * board[x][y]);
            int ny = y + (dy[i] * board[x][y]);

            int v = dfs(nx, ny);
            if (v == -1) {
                return -1;
            }

            dp[x][y] = Math.max(dp[x][y], v + 1);
        }
        visited[x][y] = false;
        return dp[x][y];
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }
}