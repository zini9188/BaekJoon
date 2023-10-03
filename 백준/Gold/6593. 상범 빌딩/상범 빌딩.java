import java.io.*;
import java.util.*;

public class Main {

    static final char WALL = '#';
    static final char EMPTY = '.';
    static final char ENTRANCE = 'S';
    static final char EXIT = 'E';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int L, R, C;
    static boolean[][][] visited;
    static char[][][] building;
    static Queue<Point> queue;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        while (true) {
            tokenizer = new StringTokenizer(br.readLine());
            L = Integer.parseInt(tokenizer.nextToken());
            R = Integer.parseInt(tokenizer.nextToken());
            C = Integer.parseInt(tokenizer.nextToken());
            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            building = new char[L][R][C];
            visited = new boolean[L][R][C];
            queue = new ArrayDeque<>();
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    building[l][r] = br.readLine().toCharArray();
                    for (int c = 0; c < C; c++) {
                        if (building[l][r][c] == ENTRANCE) {
                            queue.add(new Point(l, r, c));
                            visited[l][r][c] = true;
                        }
                    }
                }
                br.readLine();
            }
            sb.append(bfs()).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String bfs() {
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();

                for (int dir = 0; dir < 6; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    int nz = cur.z + dz[dir];
                    if (outOfRange(nx, ny, nz) || visited[nz][nx][ny]) {
                        continue;
                    }

                    if (building[nz][nx][ny] == EXIT) {
                        return String.format("Escaped in %d minute(s).", time + 1);
                    }

                    if (building[nz][nx][ny] == EMPTY) {
                        visited[nz][nx][ny] = true;
                        queue.add(new Point(nz, nx, ny));
                    }
                }
            }
            time++;
        }
        return "Trapped!";
    }

    private static boolean outOfRange(int x, int y, int z) {
        return x < 0 || y < 0 || z < 0 || x >= R || y >= C || z >= L;
    }

    static class Point {
        int z, x, y;

        public Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}