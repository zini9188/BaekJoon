import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(br.readLine());
            for (int to = 1; to <= N; to++) {
                int d = Integer.parseInt(st.nextToken());
                if (d == 0) {
                    continue;
                }

                graph.get(from).add(new Edge(to, d));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        pq.add(new Edge(1, 0));
        long ans = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.pos]) {
                continue;
            }
            visited[cur.pos] = true;
            ans += cur.dist;

            for (Edge next : graph.get(cur.pos)) {
                if (!visited[next.pos]) {
                    pq.add(next);
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Edge implements Comparable<Edge> {
        int pos, dist;

        public Edge(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }
}