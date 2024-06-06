import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static final int ROW = 12, COL = 6;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static char[][] field;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static Queue<Point> checkQ, deleteQ;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        field = new char[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            field[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        while (chain()) {
            ans++;
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static boolean chain() {
        boolean flag = false;
        visited = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (field[i][j] != '.') {
                    checkQ = new ArrayDeque<>();
                    deleteQ = new ArrayDeque<>();
                    checkQ.add(new Point(i, j));
                    deleteQ.add(new Point(i, j));
                    visited[i][j] = true;

                    bfs(i, j);

                    if (deleteQ.size() >= 4) {
                        flag = true;
                        while (!deleteQ.isEmpty()) {
                            Point point = deleteQ.poll();
                            field[point.x][point.y] = '.';
                        }
                    }
                }
            }
        }

        gravity();

        return flag;
    }

    private static void bfs(int x, int y) {
        while (!checkQ.isEmpty()) {
            Point point = checkQ.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = point.x + dx[dir];
                int ny = point.y + dy[dir];

                if (outRange(nx, ny) || visited[nx][ny]
                        || field[x][y] != field[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                checkQ.add(new Point(nx, ny));
                deleteQ.add(new Point(nx, ny));
            }
        }
    }

    private static void gravity() {
        for (int j = 0; j < COL; j++) {
            for (int i = ROW - 1; i > 0; i--) {
                if (field[i][j] == '.') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (field[k][j] != '.') {
                            field[i][j] = field[k][j];
                            field[k][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= ROW || y >= COL;
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}