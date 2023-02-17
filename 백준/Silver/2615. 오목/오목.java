import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] board;
    static int[] dx = {1, 0, 1, -1};
    static int[] dy = {0, 1, 1, 1};
    static StringBuilder builder;
    static Queue<Point> balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        board = new int[20][20];
        builder = new StringBuilder();
        balls = new LinkedList<>();

        for (int i = 1; i <= 19; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (board[i][j] != 0) {
                    balls.offer(new Point(i, j));
                }
            }
        }
        solution();
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        while (!balls.isEmpty()) {
            Point data = balls.poll();
            int x = data.x;
            int y = data.y;
            for (int i = 0; i < 4; i++) {
                int nx = x - dx[i];
                int ny = y - dy[i];
                if (!outOfRange(nx, ny) && board[nx][ny] == board[x][y]) {
                    continue;
                }

                if (findWinner(x, y, board[x][y], 1, i) == 5) {
                    builder.append(board[x][y])
                            .append("\n")
                            .append(x)
                            .append(" ")
                            .append(y);
                    return;
                }
            }
        }
        builder.append("0");
    }

    private static int findWinner(int x, int y, int find, int cnt, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (outOfRange(nx, ny) || board[nx][ny] != find) return 1;
        return 1 + findWinner(nx, ny, find, cnt + 1, dir);
    }

    private static boolean outOfRange(int x, int y) {
        return x < 1 || y < 1 || x >= 20 || y >= 20;
    }
}