import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static List<List<Integer>> graph;
    private static Island[] islands;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        islands = new Island[N + 1];
        islands[1] = new Island("S", 0, 0);
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String ti = st.nextToken();
            int ai = Integer.parseInt(st.nextToken());
            int pi = Integer.parseInt(st.nextToken());

            islands[i] = new Island(ti, ai, pi);
            graph.get(pi).add(i);
        }

        sb.append(dfs(1));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static long dfs(int index) {
        long sum = 0;
        for (Integer next : graph.get(index)) {
            sum += dfs(next);
        }

        if (islands[index].t.equals("S")) {
            return sum + islands[index].a;
        }

        return Math.max(sum - islands[index].a, 0);
    }

    private static class Island {

        String t;
        int a, p;

        public Island(String t, int a, int p) {
            this.t = t;
            this.a = a;
            this.p = p;
        }
    }
}