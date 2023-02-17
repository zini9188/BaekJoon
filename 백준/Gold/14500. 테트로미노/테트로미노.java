import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
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
                findPolyomino(i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }
    }

    private static void findPolyomino(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            result = Math.max(sum, result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (outOfRange(nx, ny) || visited[nx][ny]) continue;
            if (cnt == 2) {
                visited[nx][ny] = true;
                findPolyomino(x, y, cnt + 1, sum + board[nx][ny]);
                visited[nx][ny] = false;
            }
            visited[nx][ny] = true;
            findPolyomino(nx, ny, cnt + 1, sum + board[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    private static boolean outOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}