import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int ROW = 10, COL = 9;
    static int[] dx1 = {0, 0, -1, -1, 0, 0, 1, 1}, dy1 = {-1, -1, 0, 0, 1, 1, 0, 0};
    static int[] dx2 = {-1, 1, -1, -1, -1, 1, 1, 1}, dy2 = {-1, -1, -1, 1, 1, 1, 1, -1};


    public static void main(String[] args) throws IOException {
        int[][] dist = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            Arrays.fill(dist[i], 100000);
        }

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken()), c1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r2 = Integer.parseInt(st.nextToken()), c2 = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r1, c1});
        dist[r1][c1] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == r2 && cur[1] == c2) {
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx1[i];
                int ny = cur[1] + dy1[i];

                if (outRange(nx, ny) || isExist(nx, ny, r2, c2)) {
                    continue;
                }

                nx += dx2[i];
                ny += dy2[i];
                if (outRange(nx, ny) || isExist(nx, ny, r2, c2)) {
                    continue;
                }

                nx += dx2[i];
                ny += dy2[i];

                if (outRange(nx, ny) || dist[cur[0]][cur[1]] + 1 > dist[nx][ny]) {
                    continue;
                }

                q.add(new int[]{nx, ny});
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
            }
        }

        sb.append(dist[r2][c2]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= ROW || y >= COL;
    }

    private static boolean isExist(int r1, int c1, int r2, int c2) {
        return r1 == r2 && c1 == c2;
    }
}