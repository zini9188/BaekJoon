import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            int W = Integer.parseInt(tokenizer.nextToken());

            ArrayList<Node> graph = new ArrayList<>();
            for (int j = 0; j < M + W; j++) {
                tokenizer = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(tokenizer.nextToken());
                int E = Integer.parseInt(tokenizer.nextToken());
                int T = Integer.parseInt(tokenizer.nextToken());

                if (j < M) {
                    graph.add(new Node(S, E, T));
                    graph.add(new Node(E, S, T));
                } else {
                    graph.add(new Node(S, E, -T));
                }
            }

            if (bellmanFord(N, graph)) {
                builder.append("YES\n");
            } else {
                builder.append("NO\n");
            }
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bellmanFord(int N, ArrayList<Node> graph) {
        int[] dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            for (Node node : graph) {
                if (dist[node.E] > dist[node.S] + node.T) {
                    dist[node.E] = dist[node.S] + node.T;
                    if (i == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static class Node {
        int S, E, T;

        public Node(int S, int E, int T) {
            this.S = S;
            this.E = E;
            this.T = T;
        }
    }
}