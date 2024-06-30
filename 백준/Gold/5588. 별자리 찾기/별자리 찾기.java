import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static List<Point> zodiac;
    private static Set<Point> picture;


    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    private static void solution() {
        for (Point point : picture) {
            Point first = zodiac.get(0);
            int dx = point.x - first.x;
            int dy = point.y - first.y;

            boolean flag = false;
            for (int i = 1; i < zodiac.size(); i++) {
                Point p = zodiac.get(i);
                if (!picture.contains(new Point(p.x + dx, p.y + dy))) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                sb.append(dx).append(" ").append(dy);
            }
        }
    }

    private static void output() throws IOException {
        bw.write(sb.toString());
        bw.close();
    }

    private static void input() throws IOException {
        zodiac = new ArrayList<>();
        picture = new HashSet<>();

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            zodiac.add(new Point(x, y));
        }

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            picture.add(new Point(x, y));
        }
    }

    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}