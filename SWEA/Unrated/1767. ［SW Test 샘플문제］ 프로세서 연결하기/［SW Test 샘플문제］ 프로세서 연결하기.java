import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[][] core;
    static boolean[][] visited;
    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int length;
    static int answer;
    static int maxCount;
    static ArrayList<Point> points;
    static int c = 0;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            dfs(0, 0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int index, int count) {
        c++;
        if (index == points.size()) {
            if (maxCount < count) {
                maxCount = count;
                answer = length;
            } else if (maxCount == count) {
                answer = Math.min(answer, length);
            }
            return;
        }

        if (isOutLine(points.get(index))) {
            dfs(index + 1, count + 1);
        } else {
            for (int dir = 0; dir < 4; dir++) {
                moveDfs(index, points.get(index), dir, count);
            }
            dfs(index + 1, count);
        }
    }

    private static void moveDfs(int index, Point cp, int dir, int count) {
        Point np = cp.move(dir);
        if (!np.isInRange()) {
            dfs(index + 1, count + 1);
            return;
        }
        if (!np.isVisit() && np.isEmpty()) {
            length++;
            np.visit();
            moveDfs(index, np, dir, count);
            np.leave();
            length--;
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        core = new int[N][N];
        visited = new boolean[N][N];
        length = 0;
        answer = Integer.MAX_VALUE;
        maxCount = 0;
        points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                core[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (core[i][j] == 1) {
                    visited[i][j] = true;
                    if (i != 0 && j != 0) {
                        points.add(new Point(i, j));
                    }
                }
            }
        }
    }

    static boolean isOutLine(Point point) {
        return point.x == 0 || point.y == 0 || point.x == N - 1 || point.y == N - 1;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point move(int dir) {
            return new Point(x + dx[dir], y + dy[dir]);
        }

        public boolean isInRange() {
            return x >= 0 && y >= 0 && x < N && y < N;
        }

        public boolean isEmpty() {
            return core[x][y] == 0;
        }

        public boolean isVisit() {
            return visited[x][y];
        }

        public void visit() {
            visited[x][y] = true;
        }

        public void leave() {
            visited[x][y] = false;
        }
    }
}