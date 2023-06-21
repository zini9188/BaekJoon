import java.io.*;
import java.util.*;

public class Main {

    static Graph[] graphs;
    static int N, M, V;
    static StringBuilder answer = new StringBuilder();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        V = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N + 1];

        graphs = new Graph[N + 1];
        for (int i = 0; i <= N; i++) {
            graphs[i] = new Graph();
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            graphs[from].edges.add(to);
            graphs[to].edges.add(from);
        }

        for (Graph graph : graphs) {
            graph.edges.sort(Comparator.comparingInt(o -> o));
        }

        dfs(V);
        answer.append("\n");
        bfs(V);

        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int vertex) {
        visited[vertex] = true;
        answer.append(vertex).append(" ");
        for (Integer edge : graphs[vertex].edges) {
            if (!visited[edge]) {
                dfs(edge);
            }
        }
    }

    private static void bfs(int vertex) {
        visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            answer.append(current).append(" ");

            for (Integer edge : graphs[current].edges) {
                if (!visited[edge]) {
                    visited[edge] = true;
                    queue.add(edge);
                }
            }
        }
    }

    static class Graph {
        ArrayList<Integer> edges = new ArrayList<>();
    }
}