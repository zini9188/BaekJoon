import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] dist = new int[N + 1];
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            dist[i] = -1;
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        tokenizer = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(S, Integer.MAX_VALUE));
        dist[S] = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (dist[cur.to] > cur.w) {
                continue;
            }

            for (Node node : graph.get(cur.to)) {
                int min = Math.min(node.w, cur.w);
                if (min > dist[node.to]) {
                    dist[node.to] = min;
                    queue.add(new Node(node.to, min));
                }
            }
        }

        sb.append(dist[E]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node> {
        int to;
        int w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return o.w - this.w;
        }
    }
}