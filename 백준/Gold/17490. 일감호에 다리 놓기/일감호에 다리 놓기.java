import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int S = Integer.parseInt(st.nextToken());
            graph.get(0).add(new Node(i, S));
            graph.get(i).add(new Node(0, S));
        }

        boolean[] check = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int min = Math.min(from, to);
            int max = Math.max(from, to);
            if (min == 1 && max == N) {
                check[max] = true;
            } else {
                check[min] = true;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!check[i]) {
                if (i == N) {
                    graph.get(i).add(new Node(1, 0));
                    graph.get(1).add(new Node(i, 0));
                } else {
                    graph.get(i).add(new Node(i + 1, 0));
                    graph.get(i + 1).add(new Node(i, 0));
                }
            }
        }

        if (M <= 1) {
            sb.append("YES");
        } else {
            sb.append(prim() > K ? "NO" : "YES");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static long prim() {
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        boolean[] visited = new boolean[N + 1];
        long total = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            total += cur.weight;
            for (Node next : graph.get(cur.to)) {
                if (visited[next.to]) {
                    continue;
                }

                q.add(next);
            }
        }

        return total;
    }

    static class Node implements Comparable<Node> {

        int to;
        long weight;

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(weight, o.weight);
        }
    }
}