import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int M = read();
        int t = read();

        List<List<Node>> graph = new LinkedList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new LinkedList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();
            int c = read();

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        boolean[] visited = new boolean[N + 1];
        int ans = 0;
        int T = -t * 2;
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited[node.v]) {
                continue;
            }
            visited[node.v] = true;
            T += t;
            ans += node.w + T;

            for (Node next : graph.get(node.v)) {
                if (!visited[next.v]) {
                    q.add(next);
                }
            }
        }

        sb.append(ans + t);
        bw.write(sb.toString());
        bw.close();
        br.close();
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