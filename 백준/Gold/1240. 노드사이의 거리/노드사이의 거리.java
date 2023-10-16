import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());

            graph.get(a).add(new Node(b, d));
            graph.get(b).add(new Node(a, d));
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());

            sb.append(bfs(f, t)).append("\n");

        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(int f, int t) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(f, 0));
        boolean[] visited = new boolean[N + 1];
        visited[f] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.to == t) {
                return node.d;
            }

            for (Node next : graph.get(node.to)) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    queue.add(new Node(next.to, node.d + next.d));

                }
            }
        }
        return -1;
    }

    static class Node {
        int to, d;

        public Node(int to, int d) {
            this.to = to;
            this.d = d;
        }
    }
}