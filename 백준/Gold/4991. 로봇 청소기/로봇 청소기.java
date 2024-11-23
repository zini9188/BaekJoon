import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static StringTokenizer st;
    private static int w, h;

    public static void main(String[] args) throws IOException {
        while (input()) {

        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean input() throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        if (w == 0 && h == 0) {
            return false;
        }

        Robot robot = new Robot(new Point(0, 0), 0, 0);
        char[][] room = new char[w][h];
        int cnt = 0;
        for (int i = 0; i < w; i++) {
            room[i] = br.readLine().toCharArray();
            for (int j = 0; j < h; j++) {
                if (room[i][j] == 'o') {
                    robot = new Robot(new Point(i, j), 0, 0);
                    room[i][j] = '.';
                } else if (room[i][j] == '*') {
                    room[i][j] = (char) (cnt++ + '0');
                }
            }
        }

        int max = 1 << cnt;
        boolean[][][] visited = new boolean[1 << cnt][w][h];
        visited[robot.has][robot.p.x][robot.p.y] = true;

        Queue<Robot> q = new ArrayDeque<>();
        q.add(robot);
        while (!q.isEmpty()) {
            Robot rb = q.poll();

            if (rb.has == max - 1) {
                sb.append(rb.move).append("\n");
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = rb.p.x + dx[i];
                int ny = rb.p.y + dy[i];
                int nh = rb.has;

                if (outRange(nx, ny) || room[nx][ny] == 'x') {
                    continue;
                }

                if (Character.isDigit(room[nx][ny])) {
                    nh |= (int) Math.pow(2, (room[nx][ny] - '0'));
                }

                if (visited[nh][nx][ny]) {
                    continue;
                }

                q.add(new Robot(new Point(nx, ny), rb.move + 1, nh));
                visited[nh][nx][ny] = true;
            }
        }

        sb.append("-1\n");
        return true;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= w || y >= h;
    }

    private static class Robot {

        Point p;
        int move;
        int has;

        public Robot(Point p, int move, int has) {
            this.p = p;
            this.move = move;
            this.has = has;
        }
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}