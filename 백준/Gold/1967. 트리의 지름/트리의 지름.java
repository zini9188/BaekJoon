import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int max;
    static int node;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];

        StringTokenizer tokenizer;
        for (int i = 0; i < n - 1; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(tokenizer.nextToken());
            int e = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            graph.get(s).add(new Node(e, w));
            graph.get(e).add(new Node(s, w));
        }

        dfs(1, 0);
        
        visited = new boolean[n + 1];
        dfs(node, 0);

        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int vertex, int dist) {
        if (dist > max) {
            node = vertex;
            max = dist;
        }

        visited[vertex] = true;

        ArrayList<Node> list = graph.get(vertex);
        for (Node node : list) {
            if (!visited[node.to]) {
                visited[node.to] = true;
                dfs(node.to, dist + node.dist);
                visited[node.to] = false;
            }
        }
    }

    static class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}