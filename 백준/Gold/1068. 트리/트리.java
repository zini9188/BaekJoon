import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static List<List<Integer>> graph;
    private static int delete, ans;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int root = -1;
        for (int child = 0; child < N; child++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = child;
                continue;
            }

            graph.get(parent).add(child);
        }

        delete = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        if (delete != root) {
            dfs(root);
        }
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        int cnt = 0;
        for (Integer next : graph.get(cur)) {
            if (visited[next] || next == delete) {
                continue;
            }
            cnt++;
            dfs(next);
        }

        if (cnt == 0) {
            ans++;
        }
    }
}