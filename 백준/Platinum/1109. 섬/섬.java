import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    static List<Integer> result = new ArrayList<>();
    static boolean[][] visited;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()) + 2;
        board = new int[N + 2][M];
        visited = new boolean[N + 2][M];
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M - 2; j++) {
                board[i][j] = line.charAt(j - 1) == 'x' ? 1 : 0;
            }
        }
        N += 2;

        DFS(new HashSet<>(List.of(new Point(0, 0))));
        result.remove(result.size() - 1);
        if (result.isEmpty()) {
            sb.append("-1\n");
        } else {
            for (int res : result) {
                sb.append(res).append(" ");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int DFS(Set<Point> oceans) {
        Set<Point> islands = new HashSet<>();
        for (Point ocean : oceans) {
            if (!visited[ocean.x][ocean.y]) {
                islands.addAll(BFS(ocean.x, ocean.y, 4, 0));
            }
        }

        int height = 0;
        for (Point island : islands) {
            if (!visited[island.x][island.y]) {
                height = Math.max(height, DFS(BFS(island.x, island.y, 8, 1)) + 1);
            }
        }

        if (result.size() == height) {
            result.add(0);
        }
        result.set(height, result.get(height) + 1);
        return height;
    }

    static Set<Point> BFS(int x, int y, int d, int n) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> S = new HashSet<>();
        queue.offer(new Point(x, y));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (visited[cur.x][cur.y]) {
                continue;
            }
            visited[cur.x][cur.y] = true;
            for (int dir = 0; dir < d; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (outRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                if (board[nx][ny] == n) {
                    queue.offer(new Point(nx, ny));
                } else {
                    S.add(new Point(nx, ny));
                }
            }
        }
        return S;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}