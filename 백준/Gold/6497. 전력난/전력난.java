import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input;
        while (!(input = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(input);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < m + 1; i++) {
                graph.add(new ArrayList<>());
            }

            int total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                total += z;
                graph.get(x).add(new Edge(y, z));
                graph.get(y).add(new Edge(x, z));
            }

            Queue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(1, 0));
            boolean[] visited = new boolean[m + 1];
            int[] dist = new int[m + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            int ans = 0;
            while (!pq.isEmpty()) {
                Edge cur = pq.poll();

                if (visited[cur.pos]) {
                    continue;
                }
                visited[cur.pos] = true;
                ans += cur.dist;
                dist[cur.pos] = cur.dist;

                for (Edge next : graph.get(cur.pos)) {
                    if (!visited[next.pos] && dist[next.pos] > next.dist) {
                        pq.add(next);
                    }
                }
            }
            sb.append(total - ans).append("\n");
        }

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