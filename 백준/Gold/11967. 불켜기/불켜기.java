import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
    static int N, M;
    static Map<Point, Set<Point>> pointSetMap;
    static boolean[][] visited;
    static boolean[][] isSwitchOn;

    public static void main(String[] args) throws IOException {
        input();
        int answer = solution();
        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1][N + 1];
        isSwitchOn = new boolean[N + 1][N + 1];
        pointSetMap = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Point room1 = new Point(x, y);
            Point room2 = new Point(a, b);

            var orDefault = pointSetMap.getOrDefault(room1, new HashSet<>());
            orDefault.add(room2);
            pointSetMap.put(room1, orDefault);
        }
    }

    private static int solution() {
        int cnt = 1;
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(1, 1));
        visited[1][1] = true;
        isSwitchOn[1][1] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            Set<Point> points = pointSetMap.getOrDefault(point, new HashSet<>());
            for (Point point1 : points) {
                if (isSwitchOn[point1.x][point1.y]) {
                    continue;
                }

                cnt++;
                isSwitchOn[point1.x][point1.y] = true;
                for (int i = 0; i < 4; i++) {
                    int nx = point1.x + dx[i];
                    int ny = point1.y + dy[i];
                    if (outRange(nx, ny)) {
                        continue;
                    }

                    if (visited[nx][ny] && isSwitchOn[nx][ny]) {
                        q.add(new Point(nx, ny));
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (outRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                if (isSwitchOn[nx][ny]) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        return cnt;
    }

    public static boolean outRange(int x, int y) {
        return x < 1 || y < 1 || x > N || y > N;
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }
}