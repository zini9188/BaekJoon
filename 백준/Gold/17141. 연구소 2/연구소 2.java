import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int M, N, wall;
    private static int[] selectedVirus;
    private static List<Point> virus;
    private static int[][] labs;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
    private static int ans;

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();

        labs = new int[N][N];
        virus = new LinkedList<>();
        selectedVirus = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                labs[i][j] = read();

                if (labs[i][j] == 2) {
                    virus.add(new Point(i, j));
                } else if (labs[i][j] == 1) {
                    wall++;
                }
            }
        }

        if (M + wall == N * N || wall == N * N) {
            sb.append("0");
        } else {
            ans = Integer.MAX_VALUE;
            selectVirus(0, 0);
            sb.append(ans == Integer.MAX_VALUE ? -1 : ans);
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void selectVirus(int depth, int idx) {
        if (depth == M) {
            ans = Math.min(bfs(), ans);
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            selectedVirus[depth] = i;
            selectVirus(depth + 1, i + 1);
            selectedVirus[depth] = 0;
        }
    }

    private static int bfs() {
        int[][] spread = new int[N][N];
        Queue<Point> q = new ArrayDeque<>();
        for (int idx : selectedVirus) {
            Point p = virus.get(idx);
            q.add(new Point(p.x, p.y));
            spread[p.x][p.y] = 1;
        }

        int cnt = M;
        int time = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; !q.isEmpty() && i < size; i++) {
                Point point = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = point.x + dx[j];
                    int ny = point.y + dy[j];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N ||
                            spread[nx][ny] > 0 || labs[nx][ny] == 1) {
                        continue;
                    }

                    q.add(new Point(nx, ny));
                    cnt++;
                    spread[nx][ny] = time;
                }
            }

            if (cnt + wall == N * N) {
                return time;
            }

            time++;
        }

        return Integer.MAX_VALUE;
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