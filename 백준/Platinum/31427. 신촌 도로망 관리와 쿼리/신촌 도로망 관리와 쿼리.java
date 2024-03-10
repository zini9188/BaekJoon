import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, Q;
    static List<Edge> edges = new ArrayList<>();
    static int[] parent;
    static final int MAX_COLLEGE = 5;
    static Map<Integer, int[]> dp = new HashMap<>();
    static boolean[] visited;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        visited = new boolean[MAX_COLLEGE];
        weight = new int[MAX_COLLEGE];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int z = st.nextToken().charAt(0) - 'A';

            edges.add(new Edge(u, v, z));
        }

        dfs(0, 0);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            List<int[]> l = new ArrayList<>();
            int[] w = new int[MAX_COLLEGE];
            for (int j = 0; j < MAX_COLLEGE; j++) {
                int val = Integer.parseInt(st.nextToken());
                l.add(new int[]{j, val});
                w[j] = val;
            }

            l.sort(Comparator.comparingInt(o -> o[1]));

            int key = 0;
            for (int j = 0; j < 5; j++) {
                key += (int) ((j + 1) * Math.pow(10, MAX_COLLEGE - 1 - l.get(j)[0]));
            }

            int[] count = dp.get(key);
            long ans = 0;
            for (int j = 0; j < MAX_COLLEGE; j++) {
                ans += (long) count[j] * w[j];
            }

            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int key) {
        if (depth >= MAX_COLLEGE) {
            kruskal(key);
            return;
        }

        for (int i = 0; i < MAX_COLLEGE; i++) {
            if (!visited[i]) {
                visited[i] = true;
                weight[depth] = i;
                dfs(depth + 1, key * 10 + (i + 1));
                visited[i] = false;
                weight[depth] = 0;
            }
        }
    }

    private static void kruskal(int key) {
        edges.sort(Comparator.comparingInt(o -> weight[o.college]));
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int[] cnt = new int[MAX_COLLEGE];
        for (Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                cnt[edge.college]++;
            }
        }

        dp.put(key, cnt);
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    static class Edge {
        int u, v, college;

        public Edge(int u, int v, int college) {
            this.u = u;
            this.v = v;
            this.college = college;
        }
    }
}