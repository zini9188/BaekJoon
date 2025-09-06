import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static boolean[][] visited;
    private static int[][] farm;
    private static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1}, dy = {1, -1, 0, 0, 1, -1, 1, -1};
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        farm = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                farm[i][j] = readInt();
            }
        }

        List<Queue<Point>> sections = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (farm[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                Queue<Point> section = divideSection(i, j);
                sections.add(section);
            }
        }

        int res = 0;
        for (Queue<Point> section : sections) {
            if (!isPeak(section)) {
                continue;
            }
            res++;
        }

        sb.append(res).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isPeak(Queue<Point> section) {
        while (!section.isEmpty()) {
            Point p = section.poll();

            for (int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (outRange(nx, ny)) {
                    continue;
                }
                if (farm[nx][ny] > farm[p.x][p.y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Queue<Point> divideSection(int x, int y) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(x, y));
        visited[x][y] = true;
        Queue<Point> result = new ArrayDeque<>();
        result.offer(new Point(x, y));
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (outRange(nx, ny) || visited[nx][ny] || farm[nx][ny] != farm[cur.x][cur.y]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));
                result.offer(new Point(nx, ny));
            }
        }
        return result;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
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

        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + ", height=" + farm[x][y] + '}';
        }
    }
}