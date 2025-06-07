import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int K = readInt();
        int M = readInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N + M; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < K; j++) {
                int station = readInt();
                graph.get(station).add(i + N + 1);
                graph.get(i + N + 1).add(station);
            }
        }

        boolean[] visited = new boolean[N + M + 1];
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        visited[1] = true;

        int ans = -1;
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.v == N) {
                ans = node.dist + 1;
                break;
            }

            for (int next : graph.get(node.v)) {
                if (!visited[next]) {
                    visited[next] = true;
                    int dist = next <= N ? node.dist + 1 : node.dist;
                    q.offer(new Node(next, dist));
                }
            }
        }

        sb.append(ans).append("\n");
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
        int v, dist;

        Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}