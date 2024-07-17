import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[][] board = new int[M + 2][N];
        for (int i = 1; i <= M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        boolean[][] visited = new boolean[M + 2][N];
        int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

        boolean flag = false;
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == M + 1) {
                flag = true;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];

                if (outRange(nx, ny) || visited[nx][ny] || board[nx][ny] == 1) {
                    continue;
                }

                q.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }
        sb.append(flag ? "YES" : "NO");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x > M + 1 || y >= N;
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}