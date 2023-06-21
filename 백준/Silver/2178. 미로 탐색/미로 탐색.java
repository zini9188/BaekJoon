import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[N + 1][M + 1];
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        int answer = 987654321;
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(1, 1, 1));
        visited[1][1] = true;
        while (!points.isEmpty()) {
            Point point = points.poll();
            if (point.x == N && point.y == M) {
                answer = Math.min(answer, point.distance);
            }

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (isInRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    points.add(new Point(nx, ny, point.distance + 1));
                }
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isInRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= M;
    }

    static class Point {
        int x, y, distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}