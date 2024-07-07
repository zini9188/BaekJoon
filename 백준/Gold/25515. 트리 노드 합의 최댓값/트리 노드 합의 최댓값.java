import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static long[] weight;
    private static boolean[] visited;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        dfs(0);
        sb.append(weight[0]);
    }

    private static void dfs(int cur) {
        visited[cur] = true;

        for (Integer next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }

            dfs(next);

            weight[cur] += Math.max(0, weight[next]);
        }
    }

    private static void input() throws IOException {
        int N = Integer.parseInt(br.readLine());

        weight = new long[N + 1];
        visited = new boolean[N + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(f).add(t);
            graph.get(t).add(f);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
    }
}