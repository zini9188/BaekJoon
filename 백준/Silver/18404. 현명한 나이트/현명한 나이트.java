import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
    private static StringTokenizer st;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        Point knight = new Point(readInt(), readInt());
        int[][] dist = bfs(knight);
        for (int i = 0; i < M; i++) {
            sb.append(dist[readInt()][readInt()]).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int[][] bfs(Point knight) {
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<Point> q = new ArrayDeque<>();
        q.add(knight);
        dist[knight.x][knight.y] = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i], ny = cur.y + dy[i];
                if (nx < 1 || nx > N || ny < 1 || ny > N || dist[nx][ny] <= dist[cur.x][cur.y] + 1) {
                    continue;
                }
                q.add(new Point(nx, ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }

        }
        return dist;
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}