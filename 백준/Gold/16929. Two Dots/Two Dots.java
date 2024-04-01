import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static int M;
    static int N;
    static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        sb.append(getAnswer());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String getAnswer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                if (dfs(i, j, i, j, 1)) {
                    return "Yes";
                }
            }
        }
        return "No";
    }

    private static boolean dfs(int x, int y, int sx, int sy, int k) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (outRange(nx, ny) || map[x][y] != map[nx][ny]) {
                continue;
            }

            if (nx == sx && ny == sy && k >= 4) {
                return true;
            } else if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                if (dfs(nx, ny, sx, sy, k + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}