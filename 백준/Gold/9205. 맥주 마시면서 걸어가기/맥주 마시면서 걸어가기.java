import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            ArrayList<Point> points = new ArrayList<>();
            for (int j = 0; j < n + 2; j++) {
                tokenizer = new StringTokenizer(br.readLine());
                points.add(new Point(tokenizer.nextToken(), tokenizer.nextToken()));
            }

            sb.append(isArrive(n, points) ? "happy" : "sad").append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isArrive(int n, ArrayList<Point> points) {
        boolean[] visited = new boolean[n + 2];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next = 1; next < points.size(); next++) {
                if (!visited[next] && points.get(cur).canMove(points.get(next))) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return visited[n + 1];
    }

    static class Point {
        int x, y;

        public Point(String x, String y) {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        }

        public boolean canMove(Point p) {
            return Math.abs(this.x - p.x) + Math.abs(this.y - p.y) <= 1000;
        }
    }
}