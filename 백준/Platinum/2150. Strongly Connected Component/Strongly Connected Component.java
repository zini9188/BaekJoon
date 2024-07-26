import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static List<List<Integer>> graph, reverseGraph;
    private static int E, V;
    private static Stack<Integer> vertex;
    private static boolean[] visited;
    private static List<List<Integer>> scc;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        scc = new ArrayList<>();
        visited = new boolean[V + 1];
        while (!vertex.isEmpty()) {
            int v = vertex.pop();

            if (visited[v]) {
                continue;
            }

            scc.add(new ArrayList<>());
            kosaraju(v, scc.size() - 1);
            Collections.sort(scc.get(scc.size() - 1));
        }
        scc.sort(Comparator.comparingInt(o -> o.get(0)));

        sb.append(scc.size()).append("\n");
        for (List<Integer> list : scc) {
            for (Integer integer : list) {
                sb.append(integer).append(" ");
            }
            sb.append("-1\n");
        }
    }

    private static void kosaraju(int x, int id) {
        visited[x] = true;

        for (Integer next : reverseGraph.get(x)) {
            if (!visited[next]) {
                kosaraju(next, id);
            }
        }

        scc.get(id).add(x);
    }

    private static void dfs(int x) {
        visited[x] = true;
//        Collections.sort(graph.get(x));

        for (Integer next : graph.get(x)) {
            if (!visited[next]) {
                dfs(next);
            }
        }

        vertex.add(x);
    }

    private static void input() throws IOException {
        V = read();
        E = read();

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        vertex = new Stack<>();

        for (int v = 0; v <= V; v++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int A = read();
            int B = read();
            graph.get(A).add(B);
            reverseGraph.get(B).add(A);
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}