import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 200000 * 1000;
    static int N, E;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        E = Integer.parseInt(tokenizer.nextToken());

        dist = new int[N + 1];
        visited = new boolean[N + 1];

        Graph[] graphs = new Graph[N + 1];
        for (int i = 0; i <= N; i++) {
            graphs[i] = new Graph();
        }

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            graphs[a].addEdge(b, c);
            graphs[b].addEdge(a, c);
        }

        tokenizer = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(tokenizer.nextToken());
        int v = Integer.parseInt(tokenizer.nextToken());

        int answer = Math.min(
                dijkstra(graphs, 1, u) + dijkstra(graphs, u, v) + dijkstra(graphs, v, N),
                dijkstra(graphs, 1, v) + dijkstra(graphs, v, u) + dijkstra(graphs, u, N)
        );

        answer = answer >= MAX_VALUE ? -1 : answer;
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dijkstra(Graph[] graphs, int start, int end) {
        Arrays.fill(dist, MAX_VALUE);
        Arrays.fill(visited, false);

        PriorityQueue<Edge> vertices = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        vertices.add(new Edge(start, 0));
        dist[start] = 0;

        while (!vertices.isEmpty()) {
            Edge current = vertices.poll();
            if (visited[current.to]) continue;
            visited[current.to] = true;

            for (Edge next : graphs[current.to].edges) {
                if (dist[next.to] > dist[current.to] + next.cost) {
                    dist[next.to] = dist[current.to] + next.cost;
                    vertices.add(new Edge(next.to, dist[next.to]));
                }
            }
        }
        return dist[end];
    }

    static class Graph {
        List<Edge> edges = new ArrayList<>();

        public void addEdge(int to, int cost) {
            edges.add(new Edge(to, cost));
        }
    }

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}