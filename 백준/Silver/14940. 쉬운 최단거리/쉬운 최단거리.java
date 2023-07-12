import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<Point> points = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 2) {
                    points.add(new Point(i, j, 0));
                    visited[i][j] = true;
                    map[i][j] = 0;
                }
            }
        }

        while (!points.isEmpty()) {
            Point cur = points.poll();
            int dist = cur.dist;
            int x = cur.x;
            int y = cur.y;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (isInRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    map[nx][ny] = dist + 1;
                    points.add(new Point(nx, ny, dist + 1));
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    builder.append("-1 ");
                } else {
                    builder.append(map[i][j]).append(" ");
                }
            }
            builder.append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
