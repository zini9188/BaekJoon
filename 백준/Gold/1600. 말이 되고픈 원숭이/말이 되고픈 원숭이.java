import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int W, H;
    static int[][] board;
    static int[][] dist;
    static boolean[][][] visited;
    static int answer = Integer.MAX_VALUE;
    static int[] hdx = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int[] hdy = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());
        tokenizer = new StringTokenizer(br.readLine());
        W = Integer.parseInt(tokenizer.nextToken());
        H = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[H][W][K + 1];
        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dist = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<Monkey> queue = new ArrayDeque<>();
        queue.add(new Monkey(0, 0, K, 0));
        dist[0][0] = 0;
        visited[0][0][K] = true;

        while (!queue.isEmpty()) {
            Monkey monkey = queue.poll();
            int x = monkey.x;
            int y = monkey.y;
            int k = monkey.k;
            int c = monkey.c;

            if (x == H - 1 && y == W - 1) {
                answer = Math.min(answer, c);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isInRange(nx, ny) && !visited[nx][ny][k] && board[nx][ny] == 0) {
                    visited[nx][ny][k] = true;
                    queue.add(new Monkey(nx, ny, k, c + 1));
                }
            }

            if (k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + hdx[i];
                    int ny = y + hdy[i];
                    if (isInRange(nx, ny) && !visited[nx][ny][k - 1] && board[nx][ny] == 0) {
                        visited[nx][ny][k - 1] = true;
                        queue.add(new Monkey(nx, ny, k - 1, c + 1));
                    }
                }
            }
        }

        sb.append(answer == Integer.MAX_VALUE ? -1 : answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < H && y < W;
    }

    static class Monkey {
        int x, y, k, c;

        public Monkey(int x, int y, int k, int c) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.c = c;
        }
    }
}