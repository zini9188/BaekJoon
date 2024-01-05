import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static final int CHEESE = 1;
    static final int AIR = 0;
    static int r, c;
    static int[][] map;
    static boolean[][] visited;
    static int size = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for (int i = 0; i <r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == CHEESE) {
                    size++;
                }
            }
        }
        int time = 0;
        int cnt = 0;
        while (size != 0) {
            cnt = size;
            time++;
            bfs();
        }

        sb.append(time).append("\n");
        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void bfs() {
        Queue<Point> air = new ArrayDeque<>();
        visited = new boolean[r][c];
        air.add(new Point(0, 0));
        visited[0][0] = true;
        while (!air.isEmpty()) {
            Point cur = air.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (outRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == CHEESE) {
                    size--;
                    map[nx][ny] = AIR;
                } else if (map[nx][ny] == AIR) {
                    air.add(new Point(nx, ny));
                }
                visited[nx][ny] = true;
            }
        }
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= r || y >= c;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}