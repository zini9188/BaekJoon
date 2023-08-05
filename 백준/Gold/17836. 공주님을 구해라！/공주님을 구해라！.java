import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());

        int[][] castle = new int[N + 1][M + 1];

        for (int x = 1; x < N + 1; x++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int y = 1; y < M + 1; y++) {
                castle[x][y] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<Hero> queue = new ArrayDeque<>();
        visited[1][1] = true;
        int[][] dist = new int[N + 1][M + 1];
        queue.add(new Hero(1, 1));
        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Hero hero = queue.poll();
            int x = hero.x;
            int y = hero.y;

            if (x == N && y == M) {
                answer = Math.min(answer, dist[x][y]);
            }

            if (castle[x][y] == 2) {
                answer = Math.min(answer, dist[x][y] + M + N - x - y);
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isInRange(nx, ny) && !visited[nx][ny] && castle[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    queue.add(new Hero(nx, ny));
                    dist[nx][ny] = dist[x][y] + 1;
                }
            }
        }

        bw.write(String.valueOf(answer <= T ? answer : "Fail"));
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isInRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= M;
    }

    static class Hero {
        int x, y;

        public Hero(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}