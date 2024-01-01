import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        long total = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
            total += c;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        pq.add(new Edge(1, 0));
        long ans = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.pos]) {
                continue;
            }
            visited[cur.pos] = true;
            ans += cur.dist;
            cnt++;

            for (Edge next : graph.get(cur.pos)) {
                if (!visited[next.pos]) {
                    pq.add(next);
                }
            }
        }

        sb.append(cnt == N ? (total - ans) : -1);
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