import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[100001];
        Arrays.fill(dp, INF);

        dp[0] = 0;
        dp[2] = 1;
        dp[4] = 2;
        for (int i = 5; i <= n; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 5]) + 1;
        }
        sb.append(dp[n] == INF ? "-1" : dp[n]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}