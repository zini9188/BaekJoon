import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final char EMPTY = '.', WALL = '#';
    static final int MAX_SIZE = 8;
    static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1}, dy = {0, -1, 1, 0, -1, 1, 0, -1, 1};
    static Queue<Point> currentWalls = new ArrayDeque<>();
    static Queue<Point> movedWalls = new ArrayDeque<>();
    static char[][] board;

    public static void main(String[] args) throws IOException {
        board = new char[MAX_SIZE][MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            String line = br.readLine();
            for (int j = 0; j < MAX_SIZE; j++) {
                board[i][j] = line.charAt(j);

                if (board[i][j] == WALL) {
                    currentWalls.add(new Point(i, j));
                }
            }
        }

        sb.append(bfs());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<Point> person = new ArrayDeque<>();
        person.add(new Point(MAX_SIZE - 1, 0));
        while (!person.isEmpty()) {
            int size = person.size();

            for (int i = 0; i < size; i++) {
                Point cur = person.poll();

                if (board[cur.x][cur.y] == WALL) {
                    continue;
                }

                if (cur.x == 0 && cur.y == MAX_SIZE - 1) {
                    return 1;
                }

                for (int dir = 0; dir < 9; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (outRange(nx, ny) || board[nx][ny] == WALL) {
                        continue;
                    }

                    person.add(new Point(nx, ny));
                }
            }

            int wallSize = currentWalls.size();
            for (int i = 0; i < wallSize; i++) {
                Point cur = currentWalls.poll();

                board[cur.x][cur.y] = EMPTY;

                int nx = cur.x + 1;
                if (nx < MAX_SIZE) {
                    Point np = new Point(nx, cur.y);
                    movedWalls.add(np);
                    currentWalls.add(np);
                }
            }

            while (!movedWalls.isEmpty()) {
                Point cur = movedWalls.poll();
                board[cur.x][cur.y] = WALL;
            }
        }
        return 0;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= MAX_SIZE || y >= MAX_SIZE;
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}