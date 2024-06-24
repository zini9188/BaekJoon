import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int L, W;
    private static char[][] map;
    private static int[][] distance;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();
        solution();

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }
    }

    private static void bfs(int x, int y) {
        distance = new int[L][W];
        distance[x][y] = 1;
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (outRange(nx, ny) || map[nx][ny] == 'W' || distance[nx][ny] != 0) {
                    continue;
                }

                distance[nx][ny] = distance[point.x][point.y] + 1;
                q.add(new Point(nx, ny));
                ans = Math.max(distance[nx][ny] - 1, ans);
            }
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[L][W];
        for (int i = 0; i < L; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= L || y >= W;
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}