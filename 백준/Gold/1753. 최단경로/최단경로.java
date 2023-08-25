import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static final int MAX = 20000 * 20000;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int e = 0; e < E; e++) {
            tokenizer = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            graph.get(u).add(new Vertex(v, w));
        }

        boolean[] visited = new boolean[V + 1];
        int[] dist = new int[V + 1];
        Arrays.fill(dist, MAX);
        dist[K] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Vertex(K, 0));
        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            int num = cur.num;
            int cost = cur.cost;

            if (visited[num]) {
                continue;
            }
            visited[num] = true;

            for (Vertex next : graph.get(num)) {
                int nd = next.cost + cost;
                if (dist[next.num] > nd) {
                    dist[next.num] = nd;
                    pq.add(new Vertex(next.num, dist[next.num]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == MAX) {
                sb.append("INF\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Vertex {
        int num;
        int cost;

        public Vertex(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
