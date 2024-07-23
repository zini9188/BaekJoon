import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static StringTokenizer st;
    private static char[][] cave;
    private static int R, C;
    private static boolean[][] visited;
    private static int total;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cave = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                cave[i][j] = input.charAt(j);

                if (cave[i][j] == 'x') {
                    total++;
                }
            }
        }

        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int stick = R - Integer.parseInt(st.nextToken());

            if (i % 2 == 0) {
                for (int j = 0; j < C; j++) {
                    if (cave[stick][j] == 'x') {
                        cave[stick][j] = '.';
                        total--;
                        break;
                    }
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    if (cave[stick][j] == 'x') {
                        cave[stick][j] = '.';
                        total--;
                        break;
                    }
                }
            }

            findCluster();
        }

        print();
    }

    private static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(cave[i][j]);
            }
            sb.append("\n");
        }
    }

    private static void findCluster() {
        visited = new boolean[R][C];
        int cnt = 0;
        for (int i = 0; i < C; i++) {
            if (cave[R - 1][i] == 'x' && !visited[R - 1][i]) {
                cnt += bfs(R - 1, i);
            }
        }

        if (cnt == total) {
            return;
        }

        Queue<Point> q = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && cave[i][j] == 'x') {
                    cave[i][j] = '.';
                    q.add(new Point(i, j));
                }
            }
        }

        boolean flag = false;
        while (true) {
            for (Point p : q) {
                if (p.x + 1 > R - 1 || cave[p.x + 1][p.y] == 'x') {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                break;
            }

            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point p = q.poll();
                q.add(new Point(p.x + 1, p.y));
            }
        }

        for (Point p : q) {
            cave[p.x][p.y] = 'x';
        }
    }

    private static int bfs(int x, int y) {
        int cnt = 1;
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (outRange(nx, ny) || visited[nx][ny] || cave[nx][ny] == '.') {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
                cnt++;
            }
        }

        return cnt;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C;
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}