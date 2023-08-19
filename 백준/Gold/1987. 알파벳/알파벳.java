import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;
    static int R, C;
    static char[][] lines;
    static int result;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[] visited;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        System.out.println(result);
    }

    private static void init() throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        result = 0;
        visited = new boolean[27];
        lines = new char[R][C];
        dist = new int[R][C];

        for (int i = 0; i < R; i++) {
            lines[i] = br.readLine().toCharArray();
        }
    }

    private static void solution() {
        visited[lines[0][0] - 'A'] = true;
        dfs(0, 0, 1);
    }

    private static void dfs(int x, int y, int cnt) {
        result = Math.max(result, cnt);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isInRange(nx, ny)) {
                int nextAlpha = lines[nx][ny] - 'A';
                if (!visited[nextAlpha]) {
                    visited[nextAlpha] = true;
                    dfs(nx, ny, cnt + 1);
                    visited[nextAlpha] = false;
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }
}