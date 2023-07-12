import java.io.*;
import java.util.LinkedList;
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

        char[][] campus = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Point> position = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            campus[i] = tokenizer.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (campus[i][j] == 'I') {
                    position.add(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int count = 0;
        while (!position.isEmpty()) {
            Point cur = position.poll();            
            if (campus[cur.x][cur.y] == 'P') {
                count++;
            }            
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (isInRange(nx, ny) && campus[nx][ny] != 'X' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    position.add(new Point(nx, ny));
                }
            }
        }

        bw.write(String.valueOf(count == 0 ? "TT" : count));
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
