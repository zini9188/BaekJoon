import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static List<List<Integer>> graph, reverseGraph;
    private static Stack<Integer> stack;
    private static int N;
    private static boolean[] visited;
    private static int[] cost;
    private static List<List<Integer>> scc;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        visited = new boolean[N + 1];
        scc = new ArrayList<>();

        while (!stack.isEmpty()) {
            Integer v = stack.pop();

            if (!visited[v]) {
                scc.add(new ArrayList<>());
                kosaraju(v, scc.size() - 1);
            }
        }

        long sum = 0;
        for (List<Integer> list : scc) {
            int min = Integer.MAX_VALUE;
            for (Integer i : list) {
                min = Math.min(cost[i], min);
            }
            sum += min;
        }

        sb.append(sum);
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

        for (Integer next : graph.get(x)) {
            if (!visited[next]) {
                dfs(next);
            }
        }

        stack.add(x);
    }
    private static void input() throws IOException {
        N = read();

        cost = new int[N];
        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            cost[i] = read();
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == '1') {
                    graph.get(i).add(j);
                    reverseGraph.get(j).add(i);
                }
            }
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