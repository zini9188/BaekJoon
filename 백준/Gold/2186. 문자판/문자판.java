import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
    private static int[][][] dp;
    private static String targetWord;
    private static int N, M, K;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        targetWord = br.readLine();
        dp = new int[N][M][targetWord.length()];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        long ans = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] == targetWord.charAt(0)) {
                    ans += dfs(x, y, 0);
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int dfs(int x, int y, int idx) {
        if (dp[x][y][idx] != -1) {
            return dp[x][y][idx];
        }
        if (idx == targetWord.length() - 1) {
            return dp[x][y][idx] = 1;
        }

        dp[x][y][idx] = 0;

        for (int dir = 0; dir < 4; dir++) {
            for (int mul = 1; mul <= K; mul++) {
                int nx = x + dx[dir] * mul;
                int ny = y + dy[dir] * mul;
                int nw = targetWord.charAt(idx + 1);

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != nw) {
                    continue;
                }
                dp[x][y][idx] += dfs(nx, ny, idx + 1);
            }
        }

        return dp[x][y][idx];
    }

    private static class Point {

        int x, y, wordIdx;

        public Point(int x, int y, int wordIdx) {
            this.x = x;
            this.y = y;
            this.wordIdx = wordIdx;
        }
    }
}