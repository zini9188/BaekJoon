import java.io.*;
import java.util.*;

class Point {
    int x, y, virus;

    public Point(int x, int y, int virus) {
        this.x = x;
        this.y = y;
        this.virus = virus;
    }
}

public class Main {
    static int N, K, S, X, Y;
    static int[][] map;
    static PriorityQueue<Point> virusPoints;
    static Queue<Point> infectionPoints;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        map = new int[N + 1][N + 1];
        virusPoints = new PriorityQueue<>(Comparator.comparingInt(o -> o.virus));
        infectionPoints = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] > 0)
                    virusPoints.add(new Point(i, j, map[i][j]));
            }
        }
        tokenizer = new StringTokenizer(br.readLine());
        S = Integer.parseInt(tokenizer.nextToken());
        X = Integer.parseInt(tokenizer.nextToken());
        Y = Integer.parseInt(tokenizer.nextToken());
        for (int i = 1; i <= S; i++) {
            while (!virusPoints.isEmpty()) {
                Point data = virusPoints.poll();
                int x = data.x;
                int y = data.y;
                int virus = data.virus;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (isInRange(nx, ny) && map[nx][ny] == 0) {
                        infectionPoints.add(new Point(nx, ny, virus));
                    }
                }
            }
            while (!infectionPoints.isEmpty()) {
                Point data = infectionPoints.poll();
                if (map[data.x][data.y] == 0) {
                    map[data.x][data.y] = data.virus;
                    virusPoints.add(new Point(data.x, data.y, data.virus));
                }
            }
        }
        bw.write(map[X][Y] + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isInRange(int x, int y) {
        return x > 0 && y > 0 && x <= N && y <= N;
    }
}