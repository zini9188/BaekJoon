import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static List<int[]> teachers = new ArrayList<>();
    static int students = 0;
    static boolean[][] visited;
    static int N;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);

                if (map[i][j] == 'T') {
                    teachers.add(new int[]{i, j});
                } else if (map[i][j] == 'S') {
                    students++;
                }
            }
        }

        String ans = dfs(0) ? "YES" : "NO";
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean dfs(int depth) {
        if (depth == 3) {

            Set<Point> set = new HashSet<>();
            for (int[] teacher : teachers) {
                for (int i = 0; i < 4; i++) {
                    int nx = teacher[0] + dx[i];
                    int ny = teacher[1] + dy[i];

                    while (isInRange(nx, ny) && map[nx][ny] != 'O') {
                        if (map[nx][ny] == 'S') {
                            set.add(new Point(nx, ny));
                        }
                        nx += dx[i];
                        ny += dy[i];
                    }
                }
            }

            return set.isEmpty();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 'X') {
                    visited[i][j] = true;
                    map[i][j] = 'O';
                    if (dfs(depth + 1)) {
                        return true;
                    }
                    visited[i][j] = false;
                    map[i][j] = 'X';
                }
            }
        }

        return false;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class Point {

        int x, y;

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

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}