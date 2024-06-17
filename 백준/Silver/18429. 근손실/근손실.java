import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Plan> plans;
    static boolean[] visited;
    static int N, K;
    static int ans;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    private static void solution() {
        dfs(N, 500);
    }

    static void output() throws IOException {
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        plans = new ArrayList<>();
        plans.add(new Plan(0, 0));
        visited = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int power = Integer.parseInt(st.nextToken());
            plans.add(new Plan(i + 1, power));
        }
    }

    static void dfs(int depth, int weight) {
        if (weight < 500) {
            return;
        }

        if (depth == 0) {
            ans++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                Plan plan = plans.get(i);
                visited[i] = true;
                dfs(depth - 1, weight - K + plan.increase);
                visited[i] = false;
            }
        }
    }

    static class Plan {

        int kitId, increase;

        public Plan(int kitId, int increase) {
            this.kitId = kitId;
            this.increase = increase;
        }
    }
}