import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static Map<Integer, List<Dist>> quick = new HashMap<>();
    static int[] dp;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int D = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(tokenizer.nextToken());
            int E = Integer.parseInt(tokenizer.nextToken());
            int Q = Integer.parseInt(tokenizer.nextToken());

            if (!quick.containsKey(E)) {
                quick.put(E, new ArrayList<>());
            }
            quick.get(E).add(new Dist(S, Q));
        }

        dp = new int[D + 1];
        for (int i = 1; i <= D; i++) {
            int temp = dp[i - 1] + 1;

            if (quick.containsKey(i)) {
                for (Dist dist : quick.get(i)) {
                    temp = Math.min(temp, dp[dist.from] + dist.dist);
                }
            }

            dp[i] = temp;
        }

        sb.append(dp[D]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Dist {
        int from, dist;

        public Dist(int from, int dist) {
            this.from = from;
            this.dist = dist;
        }
    }
}