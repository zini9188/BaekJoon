import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[][] map;
    private static int N, M;
    private static Queue<Point> area;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        int idx = 1;

        Queue<Point> q = new ArrayDeque<>();
        List<Integer> weight = new ArrayList<>();
        weight.add(0);
        while (!area.isEmpty()) {
            Point point = area.poll();

            if (map[point.x][point.y] == -1 || map[point.x][point.y] > 0) {
                continue;
            }

            int cnt = 1;
            q.add(point);
            map[point.x][point.y] = idx;
            while (!q.isEmpty()) {
                Point p = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if (outRange(nx, ny) || map[nx][ny] == -1) {
                        continue;
                    }

                    if (map[nx][ny] == 0) {
                        map[nx][ny] = idx;
                        q.add(new Point(nx, ny));
                        cnt++;
                    }
                }
            }
            weight.add(cnt);
            idx++;
        }

        int[][] answer = new int[N][M];
        Set<Integer> set;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) {
                    set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (outRange(nx, ny) || map[nx][ny] == -1) {
                            continue;
                        }

                        set.add(map[nx][ny]);
                    }

                    for (Integer n : set) {
                        answer[i][j] += weight.get(n);
                    }
                    answer[i][j]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(answer[i][j] % 10);
            }
            sb.append("\n");
        }
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    private static void input() throws IOException {
        N = read();
        M = read();

        area = new ArrayDeque<>();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = System.in.read() - '0';
                if (map[i][j] == 0) {
                    area.add(new Point(i, j));
                } else {
                    map[i][j] = -1;
                }
            }
            System.in.read();
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}