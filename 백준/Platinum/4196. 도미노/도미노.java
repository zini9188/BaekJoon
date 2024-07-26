import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static List<List<Integer>> graph;
    private static Stack<Integer> stack;
    private static int N, M;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int T = read();
        for (int t = 0; t < T; t++) {
            input();
            solution();
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i, true);
            }
        }

        visited = new boolean[N];
        int cnt = 0;
        while (!stack.isEmpty()) {
            Integer v = stack.pop();

            if (!visited[v]) {
                dfs(v, false);
                cnt++;
            }
        }

        sb.append(cnt).append("\n");
    }

    private static void dfs(int x, boolean b) {
        visited[x] = true;

        for (Integer next : graph.get(x)) {
            if (!visited[next]) {
                dfs(next, b);
            }
        }

        if (b) {
            stack.add(x);
        }
    }

    private static void input() throws IOException {
        N = read();
        M = read();

        graph = new ArrayList<>();
        stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = read() - 1;
            int b = read() - 1;

            graph.get(a).add(b);
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