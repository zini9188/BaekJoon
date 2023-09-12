import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, L, R;
    static int[][] area;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        L = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());

        area = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int days = 0;
        while (union()) {
            days++;
        }

        sb.append(days);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean union() {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new ArrayDeque<>();
        ArrayList<Union> union = new ArrayList<>();
        int idx = -1;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y]) {
                    union.add(new Union(area[x][y]));
                    queue.add(new Point(x, y, idx++));
                    union.get(idx).country.add(new Point(x, y, idx));
                    visited[x][y] = true;
                    while (!queue.isEmpty()) {
                        Point p = queue.poll();
                        for (int i = 0; i < 4; i++) {
                            int nx = p.x + dx[i];
                            int ny = p.y + dy[i];

                            if (isInRange(nx, ny) && !visited[nx][ny]) {
                                int sub = Math.abs(area[nx][ny] - area[p.x][p.y]);
                                if (sub >= L && sub <= R) {
                                    Point np = new Point(nx, ny, idx);
                                    union.get(idx).country.add(np);
                                    union.get(idx).population += area[nx][ny];
                                    visited[nx][ny] = true;
                                    queue.add(np);
                                }
                            }
                        }
                    }
                }
            }
        }
        return move(union);
    }

    private static boolean move(ArrayList<Union> unions) {
        boolean flag = false;
        for (Union union : unions) {
            if (union.country.size() == 1) continue;
            flag = true;
            int moved = union.population / union.country.size();
            for (Point p : union.country) {
                area[p.x][p.y] = moved;
            }
        }
        return flag;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class Point {
        int x, y, idx;

        public Point(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    static class Union {
        ArrayList<Point> country = new ArrayList<>();
        int population;

        public Union(int population) {
            this.population = population;
        }
    }
}