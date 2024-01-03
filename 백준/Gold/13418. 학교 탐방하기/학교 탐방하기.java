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

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = (st.nextToken().equals("0") ? 1 : 0);
            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C));   
        }

        Queue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 987654321);
        int best = 0;

        boolean[] visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.pos]) {
                continue;
            }
            visited[cur.pos] = true;
            dist[cur.pos] = cur.dist;
            best += cur.dist;

            for (Edge next : graph.get(cur.pos)) {
                if (!visited[next.pos] && dist[next.pos] > next.dist) {
                    pq.add(next);
                }
            }
        }

        int worst = 0;
        pq = new PriorityQueue<>((o1, o2) -> o2.dist - o1.dist);
        pq.add(new Edge(0, 0));
        Arrays.fill(dist, 0);
        visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.pos]) {
                continue;
            }
            visited[cur.pos] = true;
            dist[cur.pos] = cur.dist;
            worst += cur.dist;

            for (Edge next : graph.get(cur.pos)) {
                if (!visited[next.pos]) {
                    pq.add(next);
                }
            }
        }

        sb.append((int) Math.pow(worst, 2) - (int) Math.pow(best, 2));
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