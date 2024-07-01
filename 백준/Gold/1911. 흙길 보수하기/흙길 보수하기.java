import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, L;
    private static Queue<Point> pq;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    private static void solution() {
        int rear = 0;
        int ans = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();

            if (p.y < rear) {
                continue;
            }

            if (p.x > rear) {
                rear = p.x;
            }

            int cnt = (p.y - rear) / L;
            if ((p.y - rear) % L != 0) {
                cnt++;
            }

            rear += cnt * L;
            ans += cnt;
        }

        System.out.println(ans);
    }

    private static void output() throws IOException {
        bw.write(sb.toString());
        bw.close();
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Point(x, y));
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
    }
}