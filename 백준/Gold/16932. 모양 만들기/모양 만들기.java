import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        Queue<Point> zero = new ArrayDeque<>();
        Queue<Point> one = new ArrayDeque<>();
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                if (Integer.parseInt(st.nextToken()) == 0) {
                    map[x][y] = -1;
                    zero.add(new Point(x, y));
                } else {
                    map[x][y] = 0;
                    one.add(new Point(x, y));
                }
            }
        }

        Queue<Point> q;
        int idx = 0;
        List<Integer> sizeOfArea = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
        while (!one.isEmpty()) {
            Point point = one.poll();
            int x = point.x;
            int y = point.y;
            if (map[x][y] == 0) {
                int cnt = 1;
                q = new ArrayDeque<>();
                q.add(new Point(x, y));
                map[x][y] = ++idx;
                while (!q.isEmpty()) {
                    Point p = q.poll();

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = p.x + dx[dir];
                        int ny = p.y + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            continue;
                        }

                        if (map[nx][ny] == 0) {
                            cnt++;
                            map[nx][ny] = idx;
                            q.add(new Point(nx, ny));
                        }
                    }
                }
                sizeOfArea.add(cnt);
            }
        }

        int ans = 0;
        while (!zero.isEmpty()) {
            Point point = zero.poll();
            int x = point.x;
            int y = point.y;
            int total = 1;
            Set<Integer> set = new HashSet<>();
            if (map[x][y] == -1) {
                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                        continue;
                    }

                    int target = map[nx][ny];
                    if (target > 0) {
                        if (!set.contains(target)) {
                            total += sizeOfArea.get(target - 1);
                            set.add(target);
                        }
                    }
                }
            }
            ans = Math.max(ans, total);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}