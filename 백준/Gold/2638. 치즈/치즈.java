import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};
    static int[][] map;
    static int N, M;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        Queue<Point> cheesePoint = new LinkedList<>();
        map = new int[N][M];
        for (int x = 0; x < N; x++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(tokenizer.nextToken());
                if (map[x][y] == 1) {
                    cheesePoint.add(new Point(x, y));
                }
            }
        }

        int time = 0;
        Queue<Point> meltPoint = new LinkedList<>();
        while (!cheesePoint.isEmpty()) {
            visited = new boolean[N][M];
            checkOutSideArea(0, 0);
            int size = cheesePoint.size();
            for (int i = 0; i < size && !cheesePoint.isEmpty(); i++) {
                Point point = cheesePoint.poll();
                if (isMelt(point.x, point.y)) {
                    meltPoint.add(point);
                } else {
                    cheesePoint.add(point);
                }
            }
            while (!meltPoint.isEmpty()) {
                Point point = meltPoint.poll();
                map[point.x][point.y] = 0;
            }
            time++;
        }

        bw.write(time + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void checkOutSideArea(int x, int y) {
        visited[x][y] = true;
        map[x][y] = -1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
                checkOutSideArea(nx, ny);
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static boolean isMelt(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (map[nx][ny] == -1) {
                count++;
            }
        }
        return count >= 2;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}