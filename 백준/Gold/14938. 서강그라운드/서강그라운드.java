import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] items;
    static List<List<Node>> graph;
    static int n, m, r;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        r = Integer.parseInt(tokenizer.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        items = new int[n + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < r; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int l = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            count = Math.max(dijkstra(i), count);
        }

        sb.append(count);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        pq.add(new Node(s, 0));
        int res = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            res += items[cur.to];

            for (Node next : graph.get(cur.to)) {
                if (!visited[next.to] && next.dist + cur.dist <= m) {
                    pq.add(new Node(next.to, next.dist + cur.dist));
                }
            }
        }

        return res;
    }

    static class Node implements Comparable<Node> {
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}