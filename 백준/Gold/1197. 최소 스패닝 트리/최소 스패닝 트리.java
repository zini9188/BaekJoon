import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int answer = 0;
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());
        
        ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            nodes.get(A).add(new Node(B, C));
            nodes.get(B).add(new Node(A, C));
        }

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        pq.add(new Node(1, 0));
        boolean[] visited = new boolean[V + 1];
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (!visited[node.from]) {
                visited[node.from] = true;
                answer += node.weight;
                for (Node nextNode : nodes.get(node.from)) {
                    if (!visited[nextNode.from]) {
                        pq.add(nextNode);
                    }
                }
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Node {
        int from;
        int weight;

        public Node(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }
}