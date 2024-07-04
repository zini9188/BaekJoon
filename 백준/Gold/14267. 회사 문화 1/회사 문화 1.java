import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static boolean[] visited;
    private static int[] weight, dp;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss == -1) {
                continue;
            }

            graph.get(boss).add(i);
        }

        weight = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int employee = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            weight[employee] += w;
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            sb.append(weight[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int cur) {
        for (Integer child : graph.get(cur)) {
            weight[child] += weight[cur];
            dfs(child);
        }
    }
}