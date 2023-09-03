import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Queue<Point>> islandPoints;
    static boolean[][] visited;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        islandPoints = new ArrayList<>();
        islandPoints.add(new ArrayDeque<>());
        visited = new boolean[N][N];
        int index = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    makeIsland(i, j, index++);
                }
            }
        }

        for (int i = 1; i < islandPoints.size(); i++) {
            visited = new boolean[N][N];
            findMinDist(i);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void findMinDist(int index) {
        Queue<Point> q = islandPoints.get(index);
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int d = cur.d;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isInRange(nx, ny)) {
                    if (board[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, d + 1));
                    } else if (board[nx][ny] != index && board[nx][ny] != 0) {
                        ans = Math.min(ans, d);
                    }
                }
            }
        }
    }

    private static void makeIsland(int x, int y, int index) {
        islandPoints.add(new ArrayDeque<>());
        Queue<Point> queue = new ArrayDeque<>();
        visited[x][y] = true;
        board[x][y] = index;
        queue.add(new Point(x, y, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isInRange(nx, ny)) {
                    if (!visited[nx][ny] && board[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        board[nx][ny] = index;
                        queue.add(new Point(nx, ny, 0));
                    } else if (board[nx][ny] == 0) {
                        cnt++;
                    }
                }
            }

            if (cnt != 0) {
                islandPoints.get(index).add(new Point(cur.x, cur.y, 0));
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }


    static class Point {
        int x, y, d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}