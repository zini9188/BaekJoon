import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static List<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(i, x1, x2, y));
        }
        Collections.sort(points);

        for (int i = 0; i < N - 1; i++) {
            Point a = points.get(i);
            Point b = points.get(i + 1);

            if (a.x2 >= b.x1) {
                union(a.idx, b.idx);
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int fa = find(a);
            int fb = find(b);

            sb.append((fa == fb) ? 1 : 0).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        }
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
        return false;
    }

    static class Point implements Comparable<Point> {
        int idx, x1, x2, y;

        public Point(int idx, int x1, int x2, int y) {
            this.idx = idx;
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return x1 - o.x1;
        }
    }
}