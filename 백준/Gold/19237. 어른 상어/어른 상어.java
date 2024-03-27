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
    // 상하좌우 -> 1,2,3,4 -> 0,1,2,3
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

//        System.out.println(Arrays.deepToString(map).replaceAll("],", "\n"));
        // 상어가 바라보는 방향
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].lookDir = Integer.parseInt(st.nextToken()) - 1;
        }

        // 상어의 방향에 따른 우선순위
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    directions[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int i = find();
        System.out.println(i);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int find() {
        // 최대 1000초
        for (int time = 1; time <= 1000; time++) {
//            System.out.println("현재 시간 : " + time);

            // 큐에 번호 큰 상어부터 이동함
            Queue<Point> q = new ArrayDeque<>();
            for (int j = M; j >= 1; j--) {
                if (isGone[j]) {
                    continue;
                }

                Point np = findNextPoint(j, sharks[j], time);
                q.add(np);

//                System.out.println(np);
            }
//            System.out.println();

            // 큐 돌면서 갱신함
            while (!q.isEmpty()) {
                Point cur = q.poll();

                // 상어 있으면 잡아먹음
                if (sharkPos[cur.x][cur.y] != null) {
//                    System.out.println(sharkPos[cur.x][cur.y] + "는 먹힌다. " + cur.sharkIdx + "에게");
                    isGone[sharkPos[cur.x][cur.y].sharkIdx] = true;
                    outShark++;
                }
                sharkPos[cur.x][cur.y] = new Point(cur.sharkIdx, cur.x, cur.y, cur.lookDir);
                sharks[cur.sharkIdx].x = cur.x;
                sharks[cur.sharkIdx].y = cur.y;
                sharks[cur.sharkIdx].lookDir = cur.lookDir;
                map[cur.x][cur.y] = new Smell(cur.sharkIdx, time + K);
            }

//            System.out.println(
//                    Arrays.deepToString(map).replaceAll("],", "\n")
//                            .replaceAll("null", "{- , -} "));
            // 잡아먹힌 상어가 M마리 -> 1번만 남았다는 뜻
            if (outShark == M) {
                return time;
            }
        }
        return -1;
    }

    private static Point findNextPoint(int sharkIdx, Point shark, int time) {
        // 현재 상어 방향에서 상어가 보는 방향에 따른 다음 방향 for문
        for (int nd : directions[sharkIdx][sharks[sharkIdx].lookDir]) {
            int nx = shark.x + dx[nd];
            int ny = shark.y + dy[nd];

            if (outRange(nx, ny)) {
                continue;
            }

            // 냄새 있는데, 만들어진 시간이 지금보다 이전이면
            if (map[nx][ny] != null && map[nx][ny].makingTime < time) {
                map[nx][ny] = null;
            }

            // 냄새 없으면 상어 움직이고 현재 좌표 없앰
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

            // 냄새 있고, 본인 번호일때만 가능
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

        @Override
        public String toString() {
            return "Point{" + "sharkIdx=" + sharkIdx + ", x=" + x + ", y=" + y + ", lookDir="
                    + lookDir + '}';
        }
    }

    static class Smell {

        int sharkIndex, makingTime;

        public Smell(int sharkIndex, int makingTime) {
            this.sharkIndex = sharkIndex;
            this.makingTime = makingTime;
        }

        @Override
        public String toString() {
            return "{" + sharkIndex +
                    ", " + makingTime +
                    '}';
        }
    }

    static class Direction {

        int[][] dir;

        public Direction() {
            // 4개의 방향에 따른 4개의 우선순위
            this.dir = new int[4][4];
        }
    }
}