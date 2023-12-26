import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] algoSpot;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        algoSpot = new int[M][N];
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                algoSpot[i][j] = line.charAt(j) - '0';
            }
        }

        int[][] destroy = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(destroy[i], 987654321);
        }

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        destroy[0][0] = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (outRange(nx, ny)) {
                    continue;
                }

                if (destroy[nx][ny] > destroy[cur.x][cur.y] + algoSpot[nx][ny]) {
                    destroy[nx][ny] = destroy[cur.x][cur.y] + algoSpot[nx][ny];
                    q.add(new Point(nx, ny));
                }
            }
        }

        sb.append(destroy[M - 1][N - 1]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= M || y >= N;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}