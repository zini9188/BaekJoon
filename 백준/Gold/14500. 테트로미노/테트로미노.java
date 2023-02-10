import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
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
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }
    }

    private static void dfs(int x, int y, int sum, int count) {
        if (count == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!outOfRange(nx, ny) && !visited[nx][ny]) {
                if (count == 2) {
                    visited[nx][ny] = true;
                    dfs(x, y, sum + board[nx][ny], count + 1);
                    visited[nx][ny] =false;
                }
                visited[nx][ny] = true;
                dfs(nx, ny, sum + board[nx][ny], count + 1);
                visited[nx][ny] = false;
            }
        }
    }

    private static boolean outOfRange(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }
}