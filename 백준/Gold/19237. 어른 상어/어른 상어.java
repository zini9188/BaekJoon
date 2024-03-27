import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static Smell[][] map;
    static Point[][] sharkPos;
    static Point[] sharks;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[][][] directions;
    static int outShark = 1;
    static boolean[] isGone;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        directions = new int[M + 1][4][4];
        map = new Smell[N + 1][N + 1];
        sharks = new Point[M + 1];
        isGone = new boolean[M + 1];
        sharkPos = new Point[N + 1][N + 1];
        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) {
                    continue;
                }

                sharks[num] = new Point(num, x, y, -1);
                map[x][y] = new Smell(num, K);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].lookDir = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    directions[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        sb.append(find());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int find() {
        for (int time = 1; time <= 1000; time++) {
            Queue<Point> q = new ArrayDeque<>();
            for (int j = M; j >= 1; j--) {
                if (isGone[j]) {
                    continue;
                }

                Point np = findNextPoint(j, sharks[j], time);
                q.add(np);
            }

            while (!q.isEmpty()) {
                Point cur = q.poll();

                if (sharkPos[cur.x][cur.y] != null) {
                    isGone[sharkPos[cur.x][cur.y].sharkIdx] = true;
                    outShark++;
                }
                sharkPos[cur.x][cur.y] = new Point(cur.sharkIdx, cur.x, cur.y, cur.lookDir);
                sharks[cur.sharkIdx].update(cur);
                map[cur.x][cur.y] = new Smell(cur.sharkIdx, time + K);
            }

            if (outShark == M) {
                return time;
            }
        }
        return -1;
    }

    private static Point findNextPoint(int sharkIdx, Point shark, int time) {
        for (int nd : directions[sharkIdx][sharks[sharkIdx].lookDir]) {
            int nx = shark.x + dx[nd];
            int ny = shark.y + dy[nd];

            if (outRange(nx, ny)) {
                continue;
            }

            if (map[nx][ny] != null && map[nx][ny].makingTime < time) {
                map[nx][ny] = null;
            }

            if (map[nx][ny] == null) {
                sharkPos[shark.x][shark.y] = null;
                return new Point(sharkIdx, nx, ny, nd);
            }
        }

        for (int nd : directions[sharkIdx][sharks[sharkIdx].lookDir]) {
            int nx = shark.x + dx[nd];
            int ny = shark.y + dy[nd];

            if (outRange(nx, ny)) {
                continue;
            }

            if (map[nx][ny] != null && map[nx][ny].sharkIndex == sharkIdx) {
                sharkPos[shark.x][shark.y] = null;
                return new Point(sharkIdx, nx, ny, nd);
            }
        }

        return null;
    }

    private static boolean outRange(int x, int y) {
        return x < 1 || y < 1 || x > N || y > N;
    }

    // 1,2,3,4 -> 상하좌우
    static class Point {

        int sharkIdx, x, y, lookDir;

        public Point(int sharkIdx, int x, int y, int lookDir) {
            this.sharkIdx = sharkIdx;
            this.x = x;
            this.y = y;
            this.lookDir = lookDir;
        }

        public void update(Point p) {
            this.x = p.x;
            this.y = p.y;
            this.lookDir = p.lookDir;
        }
    }

    static class Smell {

        int sharkIndex, makingTime;

        public Smell(int sharkIndex, int makingTime) {
            this.sharkIndex = sharkIndex;
            this.makingTime = makingTime;
        }
    }
}