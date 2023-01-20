import java.io.*;
import java.util.*;

public class Main {
    static int M, N, K, result;
    static int[][] farm;
    static Queue<int[]> queue;
    static boolean[][] visit;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static StringBuilder stringBuilder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        stringBuilder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            M = Integer.parseInt(tokenizer.nextToken());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());
            farm = new int[N][M];
            visit = new boolean[N][M];
            queue = new LinkedList<>();
            result = 0;
            for (int i = 0; i < K; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(tokenizer.nextToken());
                int x = Integer.parseInt(tokenizer.nextToken());
                farm[x][y] = 1;
            }
            solution();
        }
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && farm[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    visit[i][j] = true;
                    bfs();
                    result++;
                }
            }
        }
        stringBuilder.append(result).append("\n");
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (isPointInFarm(nx, ny) && !visit[nx][ny] && farm[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isPointInFarm(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}