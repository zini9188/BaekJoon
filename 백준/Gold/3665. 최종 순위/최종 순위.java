import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            int[] inDegree = new int[n + 1];
            boolean[][] graph = new boolean[n + 1][n + 1];

            tokenizer = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(tokenizer.nextToken());
                inDegree[num] = i - 1;

                for (int j = 1; j <= n; j++) {
                    if (num != j && !graph[j][num]) {
                        graph[num][j] = true;
                    }
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                if (graph[a][b]) {
                    graph[a][b] = false;
                    graph[b][a] = true;
                    inDegree[a]++;
                    inDegree[b]--;
                } else {
                    graph[a][b] = true;
                    graph[b][a] = false;
                    inDegree[a]--;
                    inDegree[b]++;
                }
            }

            String ans = can(n, inDegree, graph);
            System.out.println(ans);
        }

        bw.close();
        br.close();
    }

    private static String can(int n, int[] inDegree, boolean[][] graph) {
        StringBuilder b = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (queue.size() >= 2) {
                return "?";
            }

            if (queue.isEmpty()) {
                return "IMPOSSIBLE";
            }

            int cur = queue.poll();
            b.append(cur).append(" ");
            for (int j = 1; j <= n; j++) {
                if (graph[cur][j] && --inDegree[j] == 0) {
                    graph[cur][j] = false;
                    queue.add(j);
                }
            }
        }

        return b.toString();
    }
}