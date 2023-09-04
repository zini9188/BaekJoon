import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }
        sb.append(bfs(map));

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(int[][] map) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(1, 1, K, 0, 1));
        boolean[][][][] visited = new boolean[N + 1][M + 1][K + 1][2];
        visited[1][1][K][1] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.x == N && cur.y == M) {
                return cur.d + 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isInRange(nx, ny)) {
                    continue;
                }

                if (map[nx][ny] == 1 && cur.k > 0 && cur.isMorning == 1 && !visited[nx][ny][cur.k - 1][0]) {
                    visited[nx][ny][cur.k - 1][0] = true;
                    queue.add(new Point(nx, ny, cur.k - 1, cur.d + 1, 0));
                }

                if (map[nx][ny] == 0 && !visited[nx][ny][cur.k][1 - cur.isMorning]) {
                    visited[nx][ny][cur.k][1 - cur.isMorning] = true;
                    queue.add(new Point(nx, ny, cur.k, cur.d + 1, 1 - cur.isMorning));
                }
            }

            if (!visited[cur.x][cur.y][cur.k][1 - cur.isMorning]) {
                visited[cur.x][cur.y][cur.k][1 - cur.isMorning] = true;
                queue.add(new Point(cur.x, cur.y, cur.k, cur.d + 1, 1 - cur.isMorning));
            }
        }

        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= M;
    }

    static class Point {
        int x, y, k, d, isMorning;

        public Point(int x, int y, int k, int d, int isMorning) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.d = d;
            this.isMorning = isMorning;
        }
    }
}