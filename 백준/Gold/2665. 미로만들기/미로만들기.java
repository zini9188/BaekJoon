import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[][] room;
    static int n;
    static int[][] dist;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        room = new int[n][n];
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                room[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (outRange(nx, ny) || dist[nx][ny] <= dist[cur[0]][cur[1]]) {
                    continue;
                }

                dist[nx][ny] = dist[cur[0]][cur[1]] + (room[nx][ny] == 1 ? 0 : 1);
                q.add(new int[]{nx, ny});
            }
        }

        sb.append(dist[n - 1][n - 1]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}