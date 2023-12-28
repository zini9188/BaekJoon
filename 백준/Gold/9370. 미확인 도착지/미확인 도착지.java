import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int n, m, t, s, g, h;
    static List<List<Node>> graph;
    public static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    public static void main(String[] args) throws IOException {
        int T = readInt();
        for (int i = 0; i < T; i++) {
            n = readInt();
            m = readInt();
            t = readInt();

            s = readInt();
            g = readInt();
            h = readInt();

            graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < m; j++) {
                int a = readInt();
                int b = readInt();
                int d = readInt();
                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }

            List<Integer> targets = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                targets.add(readInt());
            }

            int[] dist = new int[n + 1];
            Arrays.fill(dist, 2000 * 1000 + 1);
            int[] distH = new int[n + 1];
            Arrays.fill(distH, 2000 * 1000 + 1);
            int[] distG = new int[n + 1];
            Arrays.fill(distG, 2000 * 1000 + 1);

            dijkstra(s, dist);
            dijkstra(h, distH);
            dijkstra(g, distG);

            Collections.sort(targets);
            for (Integer target : targets) {
                if (dist[target] == Math.min(dist[g] + distG[h] + distH[target], dist[h] + distH[g] + distG[target])) {
                    sb.append(target).append(" ");
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dijkstra(int start, int[] dist) {
        boolean[] visited = new boolean[n + 1];
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.pos]) {
                continue;
            }
            visited[cur.pos] = true;

            for (Node next : graph.get(cur.pos)) {
                if (dist[next.pos] > dist[cur.pos] + next.dist) {
                    dist[next.pos] = dist[cur.pos] + next.dist;
                    pq.add(new Node(next.pos, dist[next.pos]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int pos, dist;

        public Node(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }
}