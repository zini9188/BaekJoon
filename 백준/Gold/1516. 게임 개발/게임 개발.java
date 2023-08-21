import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] buildSpeed = new int[N + 1];
        int[] inDegree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(tokenizer.nextToken());
            buildSpeed[i] = time;
            while (tokenizer.hasMoreTokens()) {
                int prefer = Integer.parseInt(tokenizer.nextToken());
                if (prefer != -1) {
                    graph.get(prefer).add(i);
                    inDegree[i]++;
                }
            }
        }

        int[] cost = new int[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                cost[i] = buildSpeed[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer next : graph.get(cur)) {
                cost[next] = Math.max(cost[next], cost[cur] + buildSpeed[next]);
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i < cost.length; i++) {
            sb.append(cost[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}