import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }
        Collections.sort(points);

        int st = points.get(0).x, ed = points.get(0).y;
        List<Point> drawn = new ArrayList<>();
        for (int i = 1; i < points.size(); i++) {
            Point point = points.get(i);

            if (point.x > ed) {
                drawn.add(new Point(st, ed));
                st = point.x;
            }
            ed = Math.max(point.y, ed);
        }

        if (!drawn.isEmpty()) {
            Point point = drawn.get(drawn.size() - 1);
            if (point.x < st) {
                drawn.add(new Point(st, ed));
            }
        } else {
            drawn.add(new Point(st, ed));
        }

        int sum = 0;
        for (Point point : drawn) {
            sum += point.y - point.x;
        }

        sb.append(sum);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Point implements Comparable<Point> {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return x - o.x;
        }
    }
}