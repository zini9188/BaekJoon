import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        int s = readInt();
        int e = readInt();

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int h1 = readInt();
            int h2 = readInt();
            int k = readInt();
            graph.get(h1).add(new Node(h2, k));
            graph.get(h2).add(new Node(h1, k));
        }

        int[] dist = new int[N + 1];
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(s, Integer.MAX_VALUE));
        while (!q.isEmpty()) {
            Node data = q.poll();

            for (Node next : graph.get(data.t)) {
                int n = Math.min(next.w, data.w);
                if(n > dist[next.t]) {
                    dist[next.t] = n;
                    q.add(new Node(next.t, n));
                }
            }
        }

        sb.append(dist[e] == Integer.MAX_VALUE ? 0 : dist[e]).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }

    private static class Node implements Comparable<Node> {
        int t, w;

        public Node(int t, int w) {
            this.t = t;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return o.w - this.w;
        }
    }
}