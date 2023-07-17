import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] lab;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = 0;
    static ArrayList<Point> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        lab = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (lab[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }

        buildWall(0, 0);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void buildWall(int nx, int count) {
        if (count == 3) {
            answer = Math.max(answer, spread());
            return;
        }

        for (int i = nx; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    buildWall(nx, count + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    private static int spread() {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = lab[i].clone();
        }

        for (Point point : virus) {
            if (copy[point.x][point.y] == 2) {
                Queue<Point> q = new LinkedList<>();
                q.add(new Point(point.x, point.y));
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];

                        if (isInRange(nx, ny) && copy[nx][ny] == 0) {
                            q.add(new Point(nx, ny));
                            copy[nx][ny] = 2;
                        }
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
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
