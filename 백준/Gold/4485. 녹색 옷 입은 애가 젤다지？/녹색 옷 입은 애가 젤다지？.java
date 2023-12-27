import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        int N;
        int testCase = 1;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], 125 * 125 * 10);
            }

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{0, 0, map[0][0]});
            dist[0][0] = map[0][0];
            while (!q.isEmpty()) {
                int[] cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if (outRange(nx, ny, N)) {
                        continue;
                    }

                    if (dist[nx][ny] > dist[cur[0]][cur[1]] + map[nx][ny]) {
                        dist[nx][ny] = dist[cur[0]][cur[1]] + map[nx][ny];
                        q.add(new int[]{nx, ny, dist[nx][ny]});
                    }
                }
            }

            sb.append("Problem ").append(testCase++).append(": ").append(dist[N - 1][N - 1]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static boolean outRange(int x, int y, int N) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}