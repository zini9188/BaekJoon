import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int W = Integer.parseInt(tokenizer.nextToken());
        double M = Double.parseDouble(br.readLine());

        Position[] buildings = new Position[N + 1];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            buildings[i] = new Position(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        boolean[][] visited = new boolean[N + 1][N + 1];
        double[] dist = new double[N + 1];
        Arrays.fill(dist, Double.MAX_VALUE);
        for (int i = 0; i < W; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            visited[from][to] = true;
            visited[to][from] = true;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0.0));
        dist[1] = 0.0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int next = 1; next <= N; next++) {
                if (cur.pos == next || cur.weight >= dist[next]) {
                    continue;
                }

                if (visited[cur.pos][next]) {
                    dist[next] = cur.weight;
                    pq.add(new Node(next, cur.weight));
                    continue;
                }

                double d = getDistance(buildings[cur.pos], buildings[next]);
                if (d > M) {
                    continue;
                }

                if (dist[next] > d + dist[cur.pos]) {
                    dist[next] = d + dist[cur.pos];
                    pq.add(new Node(next, dist[next]));
                }
            }
        }

        sb.append((int)(dist[N] * 1000));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static double getDistance(Position a, Position b) {
        return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
    }

    static class Node implements Comparable<Node> {
        int pos;
        double weight;

        public Node(int pos, double weight) {
            this.pos = pos;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if (this.weight - o.weight > 0) {
                return 1;
            }
            return -1;
        }
    }

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}