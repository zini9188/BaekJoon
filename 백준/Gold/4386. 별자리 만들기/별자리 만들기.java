import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        ArrayList<Point> points = new ArrayList<>();
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(tokenizer.nextToken());
            double y = Double.parseDouble(tokenizer.nextToken());
            points.add(new Point(x, y));
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                graph.get(i).add(new Node(j, getDist(points.get(i), points.get(j))));
                graph.get(j).add(new Node(i, getDist(points.get(i), points.get(j))));
            }
        }

        double ans = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        boolean[] visited = new boolean[N];
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            ans += cur.cost;

            for (Node next : graph.get(cur.to)) {
                if (!visited[next.to]) {
                    pq.add(next);
                }
            }
        }

        sb.append(String.format("%.2f", ans));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static double getDist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2));
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        double cost;

        public Node(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) {
                return -1;
            }
            return 1;
        }
    }
}