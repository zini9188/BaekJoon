import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N, M;
    private static Point start, end;
    private static char[][] classroom;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        classroom = new char[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                classroom[i][j] = input.charAt(j - 1);

                if (classroom[i][j] == '#') {
                    classroom[i][j] = '1';
                }
            }
        }

        sb.append(simulation());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int simulation() {
        final int[] dx = {1, -1, 0, 0};
        final int[] dy = {0, 0, 1, -1};
        int jump = 0;
        Queue<Point> q;
        boolean[][] visited;
        while (classroom[end.x][end.y] == '1') {
            q = new ArrayDeque<>();
            q.add(start);
            visited = new boolean[N + 1][M + 1];
            visited[start.x][start.y] = true;
            while (!q.isEmpty()) {
                Point p = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if (outRange(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    if (classroom[nx][ny] == '1') {
                        classroom[nx][ny] = '0';
                        visited[nx][ny] = true;
                        continue;
                    }

                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
            jump++;
        }
        return jump;
    }

    private static boolean outRange(int x, int y) {
        return x < 1 || y < 1 || x > N || y > M;
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}