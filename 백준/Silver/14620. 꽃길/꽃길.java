import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static boolean[][] visited;
    private static int ans = Integer.MAX_VALUE;
    private static int[][] ground;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        N = read();

        ground = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] = read();
            }
        }

        dfs(0, 0);

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int sum) {
        if (depth == 3) {
            ans = Math.min(sum, ans);
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (!visited[i][j] && check(i, j)) {
                    visited[i][j] = true;
                    int s = ground[i][j];
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        s += ground[nx][ny];
                        visited[nx][ny] = true;
                    }
                    dfs(depth + 1, sum + s);
                    visited[i][j] = false;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }

    private static boolean check(int i, int j) {
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (visited[nx][ny]) {
                return false;
            }
        }
        return true;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}