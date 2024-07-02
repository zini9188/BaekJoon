import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static int[] set;
    private static boolean[] visited;
    private static List<Integer> answer;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    private static void solution() {
        visited = new boolean[N + 1];
        answer = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }
    }

    private static void dfs(int x, int target) {
        if (set[x] == target) {
            answer.add(target);
        }
        if (!visited[set[x]]) {
            visited[set[x]] = true;
            dfs(set[x], target);
            visited[set[x]] = false;
        }
    }

    private static void output() throws IOException {
        Collections.sort(answer);
        sb.append(answer.size()).append("\n");
        for (Integer a : answer) {
            sb.append(a).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        set = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            set[i] = Integer.parseInt(br.readLine());
        }
    }
}