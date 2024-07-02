import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static Queue<Point> lines;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    private static void solution() {
        int ans = 1;
        Queue<Integer> pq = new PriorityQueue<>();
        if (!lines.isEmpty()) {
            pq.add(lines.poll().y);
        }

        while (!lines.isEmpty()) {
            Point p = lines.poll();

            while (!pq.isEmpty() && pq.peek() <= p.x) {
                pq.poll();
            }
            pq.add(p.y);
            ans = Math.max(ans, pq.size());
        }
        sb.append(ans);
    }

    private static void output() throws IOException {
        bw.write(sb.toString());
        bw.close();
    }

    private static void input() throws IOException {
        lines = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines.add(new Point(x, y));
        }
    }

    private static class Point implements Comparable<Point> {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (x == o.x) {
                return o.y - y;
            }
            return x - o.x;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}