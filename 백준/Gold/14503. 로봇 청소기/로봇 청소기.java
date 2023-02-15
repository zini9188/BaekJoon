import java.io.*;
import java.util.*;

public class Main {
    static int N, M,count;
    static int[][] room;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        room = new int[N][M];

        tokenizer = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        int d = Integer.parseInt(tokenizer.nextToken());
        count = 1;

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dfs(r, c, d);
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int d) {
        room[x][y] = -1;
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!outOfRange(nx, ny) && room[nx][ny] == 0) {
                count++;
                dfs(nx, ny, d);
                return;
            }
        }
        int nd = (d + 2) % 4;
        int nx = x + dx[nd];
        int ny = y + dy[nd];
        if (!outOfRange(nx, ny) && room[nx][ny] != 1) {
            dfs(nx, ny, d);
        }
    }

    private static boolean outOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}