import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] seq;
    static int cnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < N + 1; i++) {
            graph.get(i).sort((o1, o2) -> o2 - o1);
        }

        seq = new int[N + 1];
        visited = new boolean[N + 1];
        dfs(R);

        for (int i = 1; i <= N; i++) {
            sb.append(seq[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dfs(int s) {
        visited[s] = true;
        seq[s] = ++cnt;
        for (Integer next : graph.get(s)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}