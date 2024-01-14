import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, 0}, dy = {0, 1};
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        int[][] area = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sb.append(bfs(area));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String bfs(int[][] area) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        boolean[][] visited = new boolean[N][N];
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int move = area[cur[0]][cur[1]];

            if (cur[0] == N - 1 && cur[1] == N - 1) {
                return "HaruHaru";
            }

            for (int i = 0; i < 2; i++) {
                int nx = cur[0] + move * dx[i];
                int ny = cur[1] + move * dy[i];

                if (outRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        
        return "Hing";
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}