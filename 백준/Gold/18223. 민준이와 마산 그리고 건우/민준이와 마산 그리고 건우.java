import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int V = read();
        int E = read();
        int P = read();

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int a = read();
            int b = read();
            int c = read();
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        int[] fromStart = dijkstra(V, graph, 1);
        int[] fromSave = dijkstra(V, graph, P);

        if (fromStart[V] == (fromSave[1] + fromSave[V])) {
            sb.append("SAVE HIM");
        } else {
            sb.append("GOOD BYE");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int[] dijkstra(int V, List<List<Node>> graph, int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, 5000 * 10000 + 1);
        dist[start] = 0;
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        boolean[] visited = new boolean[V + 1];
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited[node.v]) {
                continue;
            }
            visited[node.v] = true;

            for (Node next : graph.get(node.v)) {
                if (dist[next.v] > node.w + next.w) {
                    dist[next.v] = node.w + next.w;
                    q.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    private static class Node implements Comparable<Node> {

        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}