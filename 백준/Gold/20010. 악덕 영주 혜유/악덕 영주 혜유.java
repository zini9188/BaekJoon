import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int idx = 0;
    static int max = 0;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> connect;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        connect = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            connect.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            graph.get(from).add(new Node(from, to, weight));
            graph.get(to).add(new Node(to, from, weight));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int ans = 0;
        visited = new boolean[N + 1];
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            ans += cur.weight;
            pq.addAll(graph.get(cur.to));

            if (cur.from == cur.to) {
                continue;
            }
            
            connect.get(cur.from).add(new Node(cur.from, cur.to, cur.weight));
            connect.get(cur.to).add(new Node(cur.to, cur.from, cur.weight));
        }

        visited = new boolean[N + 1];
        visited[0] = true;
        dfs(0, 0);
        visited = new boolean[N + 1];
        visited[idx] = true;
        dfs(idx, 0);

        sb.append(ans).append("\n").append(max);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int from, int dist) {
        if (dist > max) {
            max = dist;
            idx = from;
        }

        for (Node node : connect.get(from)) {
            if (!visited[node.to]) {
                visited[node.to] = true;
                dfs(node.to, dist + node.weight);
                visited[node.to] = false;
            }
        }
    }

    static class Node implements Comparable<Node> {
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}