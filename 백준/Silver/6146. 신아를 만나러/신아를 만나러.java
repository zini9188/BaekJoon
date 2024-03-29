import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(tokenizer.nextToken()) + 500;
        int Y = Integer.parseInt(tokenizer.nextToken()) + 500;
        int N = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[1001][1001];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            map[a + 500][b + 500] = 1;
        }

        int answer = Integer.MAX_VALUE;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(500, 500, 0));
        boolean[][] visited = new boolean[1001][1001];
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            int cd = current.dist;

            if (cx == X && cy == Y) {
                answer = cd;
                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if (isInRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, cd + 1));
                }
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x <= 1000 && y <= 1000;
    }

    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}