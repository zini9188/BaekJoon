import java.io.*;
import java.util.*;

public class Main {

    static final char WALL = '#';
    static final char SPACE = '.';
    static final char FIRE = '*';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] maze;
    static int R, C;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            tokenizer = new StringTokenizer(br.readLine());
            C = Integer.parseInt(tokenizer.nextToken());
            R = Integer.parseInt(tokenizer.nextToken());

            maze = new char[R][C];
            Queue<Point> q = new ArrayDeque<>();
            Queue<Point> f = new ArrayDeque<>();
            for (int i = 0; i < R; i++) {
                maze[i] = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    if (maze[i][j] == '@') {
                        q.add(new Point(i, j));
                        maze[i][j] = SPACE;
                    }
                    if (maze[i][j] == FIRE) {
                        f.add(new Point(i, j));
                    }
                }
            }

            int ans = solution(q, f);
            sb.append(ans == -1 ? "IMPOSSIBLE" : ans).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int solution(Queue<Point> q, Queue<Point> f) {
        boolean[][] visited = new boolean[R][C];
        int time = 0;
        while (true) {
            if (q.isEmpty()) {
                return -1;
            }

            int fSize = f.size();
            for (int i = 0; i < fSize; i++) {
                Point cur = f.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (isInRange(nx, ny) && maze[nx][ny] == SPACE) {
                        f.add(new Point(nx, ny));
                        maze[nx][ny] = FIRE;
                    }
                }
            }

            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (!isInRange(nx, ny)) {
                        return time + 1;
                    }

                    if (isInRange(nx, ny) && maze[nx][ny] == '.' && !visited[nx][ny]) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            time++;
        }
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}