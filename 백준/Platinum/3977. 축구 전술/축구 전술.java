import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static List<List<Integer>> graph, reverse;
    private static List<Integer> ans;
    private static Stack<Integer> stack;
    private static int N, M;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int T = read();
        for (int t = 0; t < T; t++) {
            input();
            solution();
            sb.append("\n");
        }
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

        ans = new ArrayList<>();
        visited = new boolean[N + 1];
        int v = stack.pop();
        int cnt = check(v);

        if (cnt == N) {
            visited = new boolean[N + 1];
            kosaraju(v);
            Collections.sort(ans);
            for (Integer n : ans) {
                sb.append(n).append("\n");
            }
        } else {
            sb.append("Confused\n");
        }
    }

    private static int check(int x) {
        int cnt = 1;
        visited[x] = true;
        for (Integer next : graph.get(x)) {
            if (!visited[next]) {
                cnt += check(next);
            }
        }

        return cnt;
    }

    private static void kosaraju(int x) {
        visited[x] = true;
        for (Integer next : reverse.get(x)) {
            if (!visited[next]) {
                kosaraju(next);
            }
        }

        ans.add(x);
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
        M = read();

        graph = new ArrayList<>();
        reverse = new ArrayList<>();
        stack = new Stack<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();

            graph.get(a).add(b);
            reverse.get(b).add(a);
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        if (n == 10) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}