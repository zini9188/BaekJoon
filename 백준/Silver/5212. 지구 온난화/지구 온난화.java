import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
    private static int R;
    private static int C;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    points.add(new Point(i, j));
                }
            }
        }

        List<Point> destroy = new ArrayList<>();
        int x1 = R, x2 = 0, y1 = C, y2 = 0;
        for (Point point : points) {
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (check(nx, ny)) {
                    cnt++;
                }
            }

            if (cnt >= 3) {
                destroy.add(point);
            } else {
                x1 = Math.min(x1, point.x);
                x2 = Math.max(x2, point.x);
                y1 = Math.min(y1, point.y);
                y2 = Math.max(y2, point.y);
            }
        }

        for (Point point : destroy) {
            map[point.x][point.y] = '.';
        }

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean check(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C || map[x][y] == '.';
    }
}