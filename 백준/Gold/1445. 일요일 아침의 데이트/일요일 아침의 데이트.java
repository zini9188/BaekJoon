import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static Map<Point, Boolean> nearBy = new HashMap<>();
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        char[][] map = new char[N][M];
        Point s = null;
        List<Point> l = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    s = new Point(i, j);
                } else if (map[i][j] == 'g') {
                    l.add(new Point(i, j));
                }
            }
        }

        for (Point p : l) {
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d], ny = p.y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != '.') continue;
                nearBy.put(new Point(nx, ny), true);
            }
        }

        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(s.x, s.y, 0, 0));
        boolean[][] visit = new boolean[N][M];
        visit[s.x][s.y] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (map[node.p.x][node.p.y] == 'F') {
                sb.append(node.w1).append(" ").append(node.w2).append("\n");
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = node.p.x + dx[d], ny = node.p.y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny]) continue;
                visit[nx][ny] = true;
                if (map[nx][ny] == 'g') {
                    q.add(new Node(nx, ny, node.w1 + 1, node.w2));
                } else if (nearBy.getOrDefault(new Point(nx, ny), false)) {
                    q.add(new Node(nx, ny, node.w1, node.w2 + 1));
                } else {
                    q.add(new Node(nx, ny, node.w1, node.w2));
                }
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }


    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Node implements Comparable<Node> {
        Point p;
        int w1, w2;

        public Node(int x, int y, int w1, int w2) {
            p = new Point(x, y);
            this.w1 = w1;
            this.w2 = w2;
        }

        @Override
        public int compareTo(Node o) {
            if (o.w1 == w1) {
                return w2 - o.w2;
            }

            return w1 - o.w1;
        }
    }
}