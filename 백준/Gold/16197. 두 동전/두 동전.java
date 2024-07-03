import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    private static char[][] board;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Point> p = new ArrayList<>();
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'o') {
                    p.add(new Point(i, j));
                }
            }
        }

        sb.append(bfs(p));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(List<Point> p) {
        boolean[][][][] visited = new boolean[N][M][N][M];
        Coins coin = new Coins(p.get(0), p.get(1), 0);
        visited[coin.fir.x][coin.fir.y][coin.sec.x][coin.sec.y] = true;
        Queue<Coins> q = new ArrayDeque<>();
        q.add(coin);
        while (!q.isEmpty()) {
            Coins coins = q.poll();

            if (coins.cnt >= 10) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int fnx = coins.fir.x + dx[i];
                int fny = coins.fir.y + dy[i];
                int snx = coins.sec.x + dx[i];
                int sny = coins.sec.y + dy[i];

                if (outRange(fnx, fny) && outRange(snx, sny)) {
                    continue;
                }

                if (outRange(fnx, fny) || outRange(snx, sny)) {
                    return coins.cnt + 1;
                }

                if (board[fnx][fny] == '#' && board[snx][sny] == '#') {
                    continue;
                }

                if (board[fnx][fny] == '#') {
                    fnx = coins.fir.x;
                    fny = coins.fir.y;
                } else if (board[snx][sny] == '#') {
                    snx = coins.sec.x;
                    sny = coins.sec.y;
                }

                if (visited[fnx][fny][snx][sny]) {
                    continue;
                }

                visited[fnx][fny][snx][sny] = true;
                q.add(new Coins(new Point(fnx, fny), new Point(snx, sny), coins.cnt + 1));
            }
        }

        return -1;
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    static class Coins {
        Point fir, sec;
        int cnt;

        public Coins(Point fir, Point sec, int cnt) {
            this.fir = fir;
            this.sec = sec;
            this.cnt = cnt;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}