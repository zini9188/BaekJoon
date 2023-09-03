import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int RIGHT = 2, LEFT = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] ground;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(tokenizer.nextToken());
        int R = Integer.parseInt(tokenizer.nextToken());

        ground = new int[N][M];
        visited = new boolean[N][M];
        Queue<YoungZo> queue = new ArrayDeque<>();
        int move = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                ground[i][j] = line.charAt(j) - '0';
                if (ground[i][j] == 2) {
                    queue.add(new YoungZo(i, j, L, R));
                }
            }
        }

        while (!queue.isEmpty()) {
            YoungZo cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int l = cur.l;
            int r = cur.r;
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            move++;

            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                while (isInRange(nx, ny) && ground[nx][ny] == 0 && !visited[nx][ny]) {
                    queue.add(new YoungZo(nx, ny, l, r));
                    nx += dx[i];
                    ny += dy[i];
                }
            }
            if (l > 0) {
                int nx = x + dx[LEFT];
                int ny = y + dy[LEFT];
                if (isInRange(nx, ny) && ground[nx][ny] == 0 && !visited[nx][ny]) {
                    queue.add(new YoungZo(nx, ny, l - 1, r));
                }
            }
            if (r > 0) {
                int nx = x + dx[RIGHT];
                int ny = y + dy[RIGHT];
                if (isInRange(nx, ny) && ground[nx][ny] == 0 && !visited[nx][ny]) {
                    queue.add(new YoungZo(nx, ny, l, r - 1));
                }
            }
        }

        sb.append(move);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }


    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    static class YoungZo {
        int x;
        int y;
        int l;
        int r;

        public YoungZo(int x, int y, int l, int r) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.r = r;
        }
    }
}