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

        List<double[]> positions = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            positions.add(new double[]{x, y});
        }

        for (int i = 0; i < positions.size(); i++) {
            for (int j = 0; j < positions.size(); j++) {
                if (i == j) {
                    continue;
                }

                double dist = getDistance(positions.get(i), positions.get(j));
                graph.get(i + 1).add(new Edge(j + 1, dist));
                graph.get(j + 1).add(new Edge(i + 1, dist));
            }
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, 0));
            graph.get(b).add(new Edge(a, 0));
        }

        double ans = 0;
        pq.add(new Edge(1, 0));
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

        sb.append(String.format("%.2f", ans));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static double getDistance(double[] pos1, double[] pos2) {
        return Math.sqrt(Math.pow(Math.abs(pos1[0] - pos2[0]), 2) + Math.pow(Math.abs(pos1[1] - pos2[1]), 2));
    }

    static class Edge implements Comparable<Edge> {
        int pos;
        double dist;

        public Edge(int pos, double dist) {
            this.pos = pos;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(dist, o.dist);
        }
    }
}