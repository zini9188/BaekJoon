import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;
    static StringBuilder builder = new StringBuilder();
    static int[] buildSpeed;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());
            buildSpeed = new int[N + 1];

            tokenizer = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++) {
                buildSpeed[n] = Integer.parseInt(tokenizer.nextToken());
            }

            ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
            for (int n = 0; n <= N; n++) {
                nodes.add(new ArrayList<>());
            }

            int[] path = new int[N + 1];
            for (int k = 0; k < K; k++) {
                tokenizer = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(tokenizer.nextToken());
                int Y = Integer.parseInt(tokenizer.nextToken());
                nodes.get(X).add(Y);
                path[Y]++;
            }

            int winningBuilding = Integer.parseInt(br.readLine());

            int[] dp = new int[N + 1];

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i < N + 1; i++) {
                if (path[i] == 0) {
                    queue.add(i);
                    dp[i] = buildSpeed[i];
                }
            }

            while (!queue.isEmpty()) {
                Integer from = queue.poll();
                for (Integer to : nodes.get(from)) {
                    dp[to] = Math.max(dp[from] + buildSpeed[to], dp[to]);
                    if (--path[to] == 0) {
                        queue.add(to);
                    }
                }
            }

            builder.append(dp[winningBuilding]).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}