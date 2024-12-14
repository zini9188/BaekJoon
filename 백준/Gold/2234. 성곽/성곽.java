import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] wall = {2, 8, 4, 1}, dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
    private static int M, N;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] castle = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] result = {0, 0, 0};
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    result[1] = Math.max(result[1], bfs(castle, i, j));
                    result[0]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                        continue;
                    }

                    if ((wall[dir] & castle[i][j]) == wall[dir]) {
                        castle[i][j] -= wall[dir];
                        visited = new boolean[N][M];
                        result[2] = Math.max(result[2], bfs(castle, i, j));
                        castle[i][j] += wall[dir];
                    }
                }
            }
        }

        sb.append(result[0]).append("\n").append(result[1]).append("\n").append(result[2]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(int[][] castle, int x, int y) {
        int res = 0;
        if (!visited[x][y]) {
            int area = 1;
            Queue<Point> q = new ArrayDeque<>();
            q.add(new Point(x, y));
            visited[x][y] = true;
            while (!q.isEmpty()) {
                Point point = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = point.x + dx[dir];
                    int ny = point.y + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                        continue;
                    }

                    if ((wall[dir] & castle[point.x][point.y]) != wall[dir]) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        area++;
                    }
                }
            }
            res = Math.max(res, area);
        }
        return res;
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}