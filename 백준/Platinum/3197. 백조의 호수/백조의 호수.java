import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int R, C;
    static char[][] lake;
    static int[] parent;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Point> nearIce;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        lake = new char[R][C];
        for (int i = 0; i < R; i++) {
            lake[i] = br.readLine().toCharArray();
        }

        parent = new int[R * C];
        for (int i = 0; i < R * C; i++) {
            parent[i] = i;
        }

        List<Integer> swan = new LinkedList<>();
        Queue<Point> q = new ArrayDeque<>();
        nearIce = new ArrayDeque<>();
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (lake[i][j] == 'L') {
                    swan.add(i * C + j);
                    lake[i][j] = '.';
                }
                if (lake[i][j] == '.') {
                    melting(i, j);
                }
            }
        }
        int day = bfs(swan);

        sb.append(day);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(List<Integer> swan) {
        int day = 0;
        while (!nearIce.isEmpty()) {
            if (find(swan.get(0)) == find(swan.get(1))) {
                return day;
            }
            int size = nearIce.size();
            for (int i = 0; i < size; i++) {
                Point p = nearIce.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = p.x + dx[dir];
                    int ny = p.y + dy[dir];
                    if (isInRange(nx, ny) && lake[nx][ny] == 'X') {
                        lake[nx][ny] = '.';
                        melting(nx, ny);
                    }
                }
            }
            day++;
        }
        return day;
    }

    private static void melting(int x, int y) {
        nearIce.add(new Point(x, y));
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (isInRange(nx, ny) && lake[nx][ny] == '.') {
                nearIce.add(new Point(nx, ny));
                union(parent[nx * C + ny], parent[x * C + y]);
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}