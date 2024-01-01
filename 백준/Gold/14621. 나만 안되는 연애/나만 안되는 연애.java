import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static boolean[] isMan;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        isMan = new boolean[N + 1];
        List<List<Edge>> graph = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            isMan[i + 1] = st.nextToken().equals("M");
            graph.add(new ArrayList<>());
        }
        graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (isMan[u] && isMan[v] || !isMan[u] && !isMan[v]) {
                continue;
            }

            graph.get(u).add(new Edge(v, d));
            graph.get(v).add(new Edge(u, d));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        boolean[] visited = new boolean[N + 1];
        int ans = 0;
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

        sb.append(cnt == N ? ans : -1);
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