import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] maze;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int hx = Integer.parseInt(st.nextToken());
        int hy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());

        int[][][] dist = new int[N + 1][M + 1][2];
        maze = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(hx, hy, 1));
        dist[hx][hy][1] = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.x == ex && cur.y == ey) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nc = cur.cane;

                if (outRange(nx, ny) || nc == 0 && maze[nx][ny] == 1) {
                    continue;
                }

                if (maze[nx][ny] == 1 && nc == 1) {
                    nc--;
                }

                if (dist[nx][ny][nc] > dist[cur.x][cur.y][cur.cane] + 1) {
                    dist[nx][ny][nc] = dist[cur.x][cur.y][cur.cane] + 1;
                    q.add(new Point(nx, ny, nc));
                }
            }
        }

        int min = Math.min(dist[ex][ey][0], dist[ex][ey][1]);
        sb.append(min == Integer.MAX_VALUE ? -1 : min);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean outRange(int x, int y) {
        return x < 1 || y < 1 || x > N || y > M;
    }

    static class Point {

        int x, y, cane;

        public Point(int x, int y, int cane) {
            this.x = x;
            this.y = y;
            this.cane = cane;
        }
    }
}