import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static List<Edge> edges;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, w));
        }

        bellman();

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void bellman() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges.get(j);

                if (dist[edge.from] == INF) {
                    continue;
                }

                if (dist[edge.to] > dist[edge.from] + edge.w) {
                    dist[edge.to] = dist[edge.from] + edge.w;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            Edge edge = edges.get(i);

            if (dist[edge.from] == INF) {
                continue;
            }

            if (dist[edge.to] > dist[edge.from] + edge.w) {
                sb.append("-1");
                return;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (dist[i] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
    }

    static class Edge {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
}