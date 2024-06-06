import java.io.*;
import java.util.*;

public class Main {

    static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static boolean[][][] visited;
    static int[][] factory;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        factory = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1][4];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                factory[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int sd = getDir(Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());
        int ed = getDir(Integer.parseInt(st.nextToken()));

        int ans = bfs(sx, sy, sd, ex, ey, ed);
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(int sx, int sy, int sd, int ex, int ey, int ed) {
        Queue<Robot> q = new ArrayDeque<>();
        q.add(new Robot(sx, sy, 0, sd));
        visited[sx][sy][sd] = true;
        while (!q.isEmpty()) {
            Robot robot = q.poll();

            if (robot.x == ex && robot.y == ey && robot.look == ed) {
                return robot.cmd;
            }

            for (int i = 1; i <= 3; i++) {
                int nx = robot.x + dx[robot.look] * i;
                int ny = robot.y + dy[robot.look] * i;

                if (outRange(nx, ny) || factory[nx][ny] == 1) {
                    break;
                }

                if (visited[nx][ny][robot.look]) {
                    continue;
                }

                visited[nx][ny][robot.look] = true;
                q.add(new Robot(nx, ny, robot.cmd + 1, robot.look));
            }

            int ld = (robot.look + 3) % 4;
            int rd = (robot.look + 1) % 4;
            changeDirection(q, robot, ld);
            changeDirection(q, robot, rd);
        }
        return -1;
    }

    private static void changeDirection(Queue<Robot> q, Robot robot, int nd) {
        if (!visited[robot.x][robot.y][nd]) {
            visited[robot.x][robot.y][nd] = true;
            q.add(new Robot(robot.x, robot.y, robot.cmd + 1, nd));
        }
    }

    static int getDir(int dir) {
        if (dir == 1) {
            return 0;
        } else if (dir == 2) {
            return 2;
        } else if (dir == 3) {
            return 1;
        }
        return 3;
    }

    static boolean outRange(int x, int y) {
        return x < 1 || y < 1 || x > N || y > M;
    }

    static class Robot {

        int x, y, cmd, look;

        public Robot(int x, int y, int cmd, int look) {
            this.x = x;
            this.y = y;
            this.cmd = cmd;
            this.look = look;
        }
    }
}