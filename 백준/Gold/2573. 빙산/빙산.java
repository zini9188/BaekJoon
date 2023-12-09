import java.io.*;
import java.util.*;

public class Main {

    static final int WATER = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] seas;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Melt> melts = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        seas = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                seas[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int time = 0;
        while (!isSeparate(time)) {
            if (melts.isEmpty()) {
                sb.append("0");
                break;
            }

            while (!melts.isEmpty()) {
                Melt melt = melts.poll();
                seas[melt.x][melt.y] = Math.max(0, seas[melt.x][melt.y] - melt.amount);
            }
            time++;
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isSeparate(int time) {
        boolean[][] visited = new boolean[N][M];
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (seas[i][j] > WATER && !visited[i][j]) {
                    Queue<Melt> queue = new ArrayDeque<>();
                    queue.add(new Melt(i, j));
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        Melt cur = queue.poll();

                        int amount = 0;
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];

                            if (outRange(nx, ny) || visited[nx][ny]) {
                                continue;
                            }

                            if (seas[nx][ny] > WATER) {
                                queue.add(new Melt(nx, ny));
                                visited[nx][ny] = true;
                            } else {
                                amount++;
                            }
                        }

                        if (amount > 0) {
                            melts.add(new Melt(cur.x, cur.y, amount));
                        }
                    }

                    area++;
                    if (area > 1) {
                        sb.append(time);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    private static class Melt {
        int x, y, amount;

        public Melt(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }

        public Melt(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}