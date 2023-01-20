import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] floor;
    static boolean[][] visited;
    static Queue<int[]> queue;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        floor = new char[N][M];
        visited = new boolean[N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String lines = br.readLine();
            for (int j = 0; j < M; j++) {
                floor[i][j] = lines.charAt(j);
            }
        }
        solution();
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});
                    if (floor[i][j] == '-') {
                        horizontalBFS();
                    } else if (floor[i][j] == '|') {
                        verticalBFS();
                    }
                    result++;
                }
            }
        }
    }

    private static void horizontalBFS() {
        bfs(0, 1);
    }

    private static void verticalBFS() {
        bfs(1, 0);
    }

    private static void bfs(int dx, int dy) {
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            int nx = x + dx;
            int ny = y + dy;
            addPoint(x, y, nx, ny);
        }
    }

    private static void addPoint(int x, int y, int nx, int ny) {
        if (isPointInRange(nx, ny) && !visited[nx][ny] && floor[x][y] == floor[nx][ny]) {
            visited[nx][ny] = true;
            queue.add(new int[]{nx, ny});
        }
    }

    private static boolean isPointInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}