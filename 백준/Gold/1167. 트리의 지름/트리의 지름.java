import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static ArrayList<ArrayList<Tree>> trees = new ArrayList<>();
    static boolean[] visited;
    static int answer = 0, node = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine());
        for (int i = 0; i <= V; i++) {
            trees.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            while (tokenizer.hasMoreTokens()) {
                int end = Integer.parseInt(tokenizer.nextToken());
                if (end == -1) {
                    break;
                }
                int dist = Integer.parseInt(tokenizer.nextToken());
                trees.get(start).add(new Tree(end, dist));
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(node, 0);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int diameter) {
        if (diameter > answer) {
            answer = diameter;
            node = index;
        }

        visited[index] = true;
        for (Tree tree : trees.get(index)) {
            if (!visited[tree.end]) {
                visited[tree.end] = true;
                dfs(tree.end, diameter + tree.dist);
            }
        }
    }

    static class Tree {
        int end, dist;

        public Tree(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }
}